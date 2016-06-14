package com.pz.monitor.requests;

import com.pz.monitor.database.DatabaseTables;

import javax.ws.rs.QueryParam;
import java.util.Date;

public class SimpleSensorDetailsRequest implements Request {
    @QueryParam("SensorId")
    public String sensorId;

    @QueryParam("Measurement")
    public String measurement;

    @QueryParam("StartTime")
    public Long startTime;

    @QueryParam("EndTime")
    public Long endTime;

    @QueryParam("MaxCount")
    public Integer maxCount;

    @Override
    public void reassignValues() {
        endTime = endTime == null ? new Date().getTime() : endTime;
        startTime = startTime == null ? 0 : startTime;
        maxCount = maxCount == null ? Integer.MAX_VALUE : maxCount;
        measurement = DatabaseTables.measurementMapper.get(measurement).toString();
    }

    @Override
    public void validate() {
        if (sensorId == null || measurement == null)
            throw new IllegalStateException("sensorId and measurement is required.");
        if (!DatabaseTables.measurementMapper.containsKey(measurement))
            throw new IllegalStateException("invalid measurement name");
    }

}
