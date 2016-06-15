package com.pz.monitor.requests;

import javax.ws.rs.QueryParam;

public class MetricRequest implements Request {
    @QueryParam("description")
    public String description;

    @QueryParam("metricType")
    public MetricType metricType = MetricType.SIMPLE;

    @QueryParam("measure")
    public Measure measure = Measure.CPU;

    @QueryParam("userId")
    public Integer userId;

    @QueryParam("interval")
    public Integer interval;

    @QueryParam("windowSize")
    public Integer windowSize;


    @Override
    public void reassignValues() {
    }

    @Override
    public void validate() {
        boolean isCorrect = description!=null && userId!=null && interval!=null && windowSize!=null;
        if (!isCorrect) throw new IllegalStateException("Invalid payload");
    }

    public enum Measure {
        CPU,
        MEMORY,
        NETWORK;
    }

    public enum MetricType {
        SIMPLE, COMPLEX
    }
}
