package com.project.pz.webserver.exception;

/**
 * Created by Piotr Sołtysiak on 2016-06-13.
 * Contact: piotrek.soltysiak@gmail.com
 */
public final class MetricNotFoundException extends Exception {

    private final String monitorId;

    private final String sensorId;

    private final String metricId;

    public MetricNotFoundException(String monitorId, String sensorId, String metricId) {
        this.monitorId = monitorId;
        this.sensorId = sensorId;
        this.metricId = metricId;
    }

    public String getMonitorId() {
        return monitorId;
    }

    public String getSensorId() {
        return sensorId;
    }

    public String getMetricId() {
        return metricId;
    }
}
