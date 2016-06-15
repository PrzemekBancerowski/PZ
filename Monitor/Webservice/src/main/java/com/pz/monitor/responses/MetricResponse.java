package com.pz.monitor.responses;

import com.pz.monitor.requests.MetricRequest;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MetricResponse {
    private final Integer id;
    private final String description;
    private final MetricRequest.MetricType metricType;
    private final MetricRequest.Measure measure;
    private final Integer userId;
    private final Integer interval;
    private final Integer windowSize;

    public MetricResponse(ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            this.id= resultSet.getInt("id");
            this.description= resultSet.getString("description");
            this.metricType = MetricRequest.MetricType.valueOf(resultSet.getString("metricType"));
            this.measure = MetricRequest.Measure.valueOf(resultSet.getString("measure"));
            this.userId= resultSet.getInt("userId");
            this.windowSize= resultSet.getInt("windowSize");
            this.interval= resultSet.getInt("interval");
        } else {
            throw new IllegalArgumentException("Invalid sensorId");
        }
    }

    public MetricResponse(MetricRequest request, ResultSet result) throws SQLException {
        if (result.next()) {
            this.id= result.getInt("id");
            this.description = request.description;
            this.metricType = request.metricType;
            this.measure = request.measure;
            this.userId = request.userId;
            this.windowSize = request.windowSize;
            this.interval= request.interval;
        } else {
            throw new IllegalArgumentException("Error getting metricsId");
        }
    }
}
