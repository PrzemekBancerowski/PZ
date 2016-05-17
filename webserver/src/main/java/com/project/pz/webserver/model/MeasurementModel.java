package com.project.pz.webserver.model;

import com.project.pz.webserver.dict.Measure;
import com.project.pz.webserver.dict.Operation;

import java.util.List;

/**
 * Created by Piotr Sołtysiak on 2016-05-17.
 * Contact: piotrek.soltysiak@gmail.com
 */
public class MeasurementModel {

    private Integer id;

    private String measurementName;

    private Operation operation;

    private Measure measure;

    private Long startTime;

    private Long endTime;

    private List<SensorModel> sensors;

    public MeasurementModel() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMeasurementName() {
        return measurementName;
    }

    public void setMeasurementName(String measurementName) {
        this.measurementName = measurementName;
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public Measure getMeasure() {
        return measure;
    }

    public void setMeasure(Measure measure) {
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
