package com.project.pz.webserver.service;

import com.project.pz.webserver.exception.MetricNotFoundException;
import com.project.pz.webserver.model.MetricModel;

import java.util.List;

/**
 * Created by Piotr Sołtysiak on 2016-06-13.
 * Contact: piotrek.soltysiak@gmail.com
 */
public interface MetricService {

    List<MetricModel> getMetrics(String monitorId, String sensorId);

    MetricModel getMetric(String monitorId, String sensorId, String metricId) throws MetricNotFoundException;

    MetricModel addMetric(String monitorId, String sensorId, MetricModel metric);

    void removeMetric(String monitorId, String sensorId, String metricId);
}
