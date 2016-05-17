package com.project.pz.webserver.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.pz.webserver.model.SensorModel;

import java.util.List;

/**
 * Created by Piotr Sołtysiak on 2016-05-17.
 * Contact: piotrek.soltysiak@gmail.com
 */
public class ComplexSensorDetailsRequest {

    @JsonProperty("Operation")
    public String operation;

    @JsonProperty("Measure")
    public String measure;

    @JsonProperty("StartTime")
    public Long startTime;

    @JsonProperty("EndTime")
    public Long endTime;

    public List<SensorModel> sensors;

    public ComplexSensorDetailsRequest() {
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public List<SensorModel> getSensors() {
        return sensors;
    }

    public void setSensors(List<SensorModel> sensors) {
        this.sensors = sensors;
    }
}
