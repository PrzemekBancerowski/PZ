package com.pz.monitor.database;

import com.pz.monitor.database.DatabaseTables.SENSOR_DETAILS;
import com.pz.monitor.database.DatabaseTables.SENSOR_METADATA;
import com.pz.monitor.database.DatabaseTables.METRICS;
import com.pz.monitor.helpers.CreateTableHelper;
import com.pz.monitor.requests.ComplexSensorDetailsRequest;
import com.pz.monitor.requests.MetricRequest;
import com.pz.monitor.requests.SimpleSensorDetailsRequest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.stream.Collectors;

public class QueryFactory {
    private Connection dbConnection;

    public QueryFactory(Connection connection) {
        this.dbConnection = connection;
    }

    public Query hostQuery() throws SQLException {
        String sqlQuery = " SELECT " + SENSOR_METADATA.sensorId + " as id, " + SENSOR_METADATA.hostname + " as host " +
                          " FROM " + DatabaseTables.SENSOR_METADATA_TABLE+
                          " GROUP BY " + SENSOR_METADATA.sensorId + ", " + SENSOR_METADATA.hostname;

        PreparedStatement statement = dbConnection.prepareStatement(sqlQuery);
        return new Query(statement);
    }

    public Query sensorQuery(String id) throws SQLException {
        String sqlQuery = " SELECT * FROM " + DatabaseTables.SENSOR_METADATA_TABLE +
                          " WHERE " + SENSOR_METADATA.sensorId + " = ?";

        PreparedStatement statement = dbConnection.prepareStatement(sqlQuery);
        statement.setString(1, id);
        return new Query(statement);
    }

    public Query sensorsQuery() throws SQLException {
        String sqlQuery = " SELECT * FROM " + DatabaseTables.SENSOR_METADATA_TABLE;

        PreparedStatement statement = dbConnection.prepareStatement(sqlQuery);
        return new Query(statement);
    }

    public Query fromRequest(ComplexSensorDetailsRequest request) throws SQLException {
        String sensorIds = request.sensors.stream().map(sensor -> "'"+sensor.id+"'").collect(Collectors.joining(", "));
        String sqlQuery = " SELECT " + request.Operation + "(" + request.Measure + ") as val " +
                          " FROM " + DatabaseTables.SENSOR_DETAILS_TABLE +
                          " WHERE " + SENSOR_DETAILS.createdOn + " > ?" +
                          " AND " + SENSOR_DETAILS.createdOn + " < ?"+
                          " AND " + SENSOR_DETAILS.sensorId + " IN (" + sensorIds + ") " +
                          " GROUP BY " + request.Measure;

        PreparedStatement statement = dbConnection.prepareStatement(sqlQuery);
        statement.setLong(1, request.StartTime);
        statement.setLong(2, request.EndTime);
        return new Query(statement);
    }

    public Query fromRequest(SimpleSensorDetailsRequest request) throws SQLException {
        String sqlQuery = " SELECT "+request.measurement+" as val FROM " + DatabaseTables.SENSOR_DETAILS_TABLE +
                          " WHERE " + SENSOR_DETAILS.createdOn + " > ?" +
                          " AND " + SENSOR_DETAILS.createdOn + " < ?" +
                          " AND " +  SENSOR_DETAILS.sensorId + " = ? " +
                          " LIMIT ?";

        PreparedStatement statement = dbConnection.prepareStatement(sqlQuery);
        statement.setLong(1, request.startTime);
        statement.setLong(2, request.endTime);
        statement.setString(3, request.sensorId);
        statement.setInt(4, request.maxCount);
        return new Query(statement);
    }

    public Query resetDb() throws SQLException {
        String sqlQuery = CreateTableHelper.initTablesQuery();
        PreparedStatement statement = dbConnection.prepareStatement(sqlQuery);
        return new Query(statement);
    }

    public Query metricsQuery(String sensorId, int metricsId) throws SQLException {
        String sqlQuery = " SELECT * FROM " + DatabaseTables.METRICS_TABLE +
            " WHERE " + METRICS.id + " = ?" +
            " AND " +  METRICS.sensorId + " = ? ";

        PreparedStatement statement = dbConnection.prepareStatement(sqlQuery);
        statement.setInt(1, metricsId);
        statement.setString(2, sensorId);
        return new Query(statement);
    }

    public Query createMetrics(MetricRequest request, String sensorId) throws SQLException {
        String sqlQuery = "INSERT INTO " + DatabaseTables.METRICS_TABLE +
            " ("+METRICS.sensorId+", "+METRICS.description+", "+METRICS.metricType+", "+METRICS.measure+", "+METRICS.userId+", "+METRICS.interval+", "+METRICS.windowSize+")\n " +
            " values (?,?,?,?,?,?,?) RETURNING " + METRICS.id;

        PreparedStatement statement = dbConnection.prepareStatement(sqlQuery);
        statement.setString(1, sensorId);
        statement.setString(2, request.description);
        statement.setString(3, request.metricType.toString());
        statement.setString(4, request.measure.toString());
        statement.setInt(5, request.userId);
        statement.setInt(6, request.interval);
        statement.setInt(7, request.windowSize);
        return new Query(statement);
    }

    public Query metricsQuery(String sensorId) throws SQLException {
        String sqlQuery = " SELECT * FROM " + DatabaseTables.METRICS_TABLE +
            " WHERE " + METRICS.sensorId + " = ? ";

        PreparedStatement statement = dbConnection.prepareStatement(sqlQuery);
        statement.setString(1, sensorId);
        return new Query(statement);
    }

    public Query deleteMetricQuery(String sensorId, int metricId) throws SQLException {
        String sqlQuery = "DELETE FROM " + DatabaseTables.METRICS_TABLE +
                        " WHERE " + METRICS.id + " = ?" +
                        " AND " +  METRICS.sensorId + " = ? ";

        PreparedStatement statement = dbConnection.prepareStatement(sqlQuery);
        statement.setInt(1, metricId);
        statement.setString(2, sensorId);
        return new Query(statement);
    }
}
