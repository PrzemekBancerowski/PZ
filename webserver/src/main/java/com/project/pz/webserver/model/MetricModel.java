package com.project.pz.webserver.model;

import com.project.pz.webserver.dict.Measure;
import com.project.pz.webserver.dict.MetricType;

/**
 * Created by Piotr Sołtysiak on 2016-06-13.
 * Contact: piotrek.soltysiak@gmail.com
 */
public class MetricModel {

    private String id;

    private String description;

    private MetricType metricType;

    private Measure measure;

    private String userId;

    private Integer interval;

    private Integer windowSize;

    public MetricModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public MetricType getMetricType() {
        return metricType;
    }

    public void setMetricType(MetricType metricType) {
        this.metricType = metricType;
    }

    public Measure getMeasure() {
        return measure;
    }

    public void setMeasure(Measure measure) {
        this.measure = measure;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getInterval() {
        return interval;
    }

    public void setInterval(Integer interval) {
        this.interval = interval;
    }

    public Integer getWindowSize() {
        return windowSize;
    }

    public void setWindowSize(Integer windowSize) {
        this.windowSize = windowSize;
    }

    @Override
    public String toString() {
        return "MetricModel{" +
                "id='" + id + '\'' +
                ", description='" + description + '\'' +
                ", metricType=" + metricType +
                ", measure=" + measure +
                ", userId='" + userId + '\'' +
                ", interval=" + interval +
                ", windowSize=" + windowSize +
                '}';
    }
}
