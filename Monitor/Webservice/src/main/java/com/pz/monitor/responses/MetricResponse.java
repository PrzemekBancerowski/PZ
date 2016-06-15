package com.pz.monitor.responses;

import com.pz.monitor.requests.MetricRequest;

public class MetricResponse {
    private final String id;
    private final String description;
    private final String metricType;
    private final String measure;
    private final String userId;
    private final Integer interval;
    private final Integer windowSize;

    public MetricResponse(MetricRequest request) {
        this.id = request.id;
        this.description = request.description;
        this.metricType = request.metricType;
        this.measure = request.measure;
        this.userId = request.userId;
        this.windowSize = request.windowSize;
        this.interval = request.interval;
    }

    public MetricResponse() {
        id = "metricId";
        description = "metric desc";
        metricType = "metric type";
        measure = "metric measure";
        userId = "user id ";
        interval = 100;
        windowSize = 230;
    }
}
