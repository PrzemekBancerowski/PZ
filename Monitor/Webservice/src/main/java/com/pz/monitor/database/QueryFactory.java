package com.pz.monitor.database;

import com.pz.monitor.database.DatabaseTables.SENSOR_DETAILS;
import com.pz.monitor.database.DatabaseTables.SENSOR_METADATA;
import com.pz.monitor.requests.ComplexSensorDetailsRequest;
import com.pz.monitor.requests.SimpleSensorDetailsRequest;

import java.util.stream.Collectors;

public class QueryFactory {

    public Query hostQuery() {
        String sqlQuery = " SELECT " + SENSOR_METADATA.sensorId + " as id, " + SENSOR_METADATA.hostname + " as host " +
                          " FROM " + DatabaseTables.SENSOR_METADATA_TABLE+
                          " GROUP BY " + SENSOR_METADATA.sensorId + ", " + SENSOR_METADATA.hostname + ";";
        return new Query(sqlQuery);
    }

    public Query sensorQuery(String id) {
        String sqlQuery = " SELECT * FROM " + DatabaseTables.SENSOR_METADATA_TABLE+
                          " WHERE " + SENSOR_METADATA.sensorId + " = '" + id + "';";
        return new Query(sqlQuery);
    }

    public Query fromRequest(ComplexSensorDetailsRequest request) {
        String sensorIds = request.sensors.stream().map(sensor -> "'"+sensor.id+"'").collect(Collectors.joining(", "));
        String sqlQuery =
                " SELECT " + request.Operation + "(" + request.Measure + ") as val FROM " + DatabaseTables.SENSOR_DETAILS_TABLE +
                " WHERE " + SENSOR_DETAILS.createdOn + " > " + request.StartTime +
                    " AND " + SENSOR_DETAILS.createdOn + " < " + request.EndTime +
                    " AND " + SENSOR_DETAILS.sensorId + " IN (" + sensorIds + ") " +
                " GROUP BY " + request.Measure + ";";
        return new Query(sqlQuery);
    }

    public Query fromRequest(SimpleSensorDetailsRequest request) {
        String sqlQuery =
                        " SELECT " + request.measurement + " as val FROM " + DatabaseTables.SENSOR_DETAILS_TABLE +
                        " WHERE " + SENSOR_DETAILS.createdOn + " > " + request.startTime +
                        " AND " + SENSOR_DETAILS.createdOn + " < " + request.endTime +
                        " AND " +  SENSOR_DETAILS.sensorId + " = '" + request.sensorId + "' " +
                        " LIMIT " + request.maxCount + ";";
        return new Query(sqlQuery);
    }
}
