package com.project.pz.webserver.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Piotr Sołtysiak on 2016-05-17.
 * Contact: piotrek.soltysiak@gmail.com
 */
public class SimpleSensorDetailsRequest  {

    @JsonProperty("SensorId")
    public final String sensorId;

    @JsonProperty("Measurement")
    public final String measurement;

    @JsonProperty("StartTime")
    public final Long startTime;

    @JsonProperty("EndTime")
    public final Long endTime;

    @JsonProperty("MaxCount")
    public final Integer maxCount;

    public SimpleSensorDetailsRequest(String sensorId, String measurement, Long startTime, Long endTime, Integer maxCount) {
        this.sensorId = sensorId;
        this.measurement = measurement;
        this.startTime = startTime;
        this.endTime = endTime;
        this.maxCount = maxCount;
    }

    public String getSensorId() {
        return sensorId;
    }

    public String getMeasurement() {
        return measurement;
    }

    public Long getStartTime() {
        return startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public Integer getMaxCount() {
        return maxCount;
    }
}
