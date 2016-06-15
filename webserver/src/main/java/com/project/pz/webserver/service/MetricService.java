package com.project.pz.webserver.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.project.pz.webserver.exception.MetricNotFoundException;
import com.project.pz.webserver.exception.MetricOwnerException;
import com.project.pz.webserver.exception.MonitorException;
import com.project.pz.webserver.exception.MonitorNotFoundException;
import com.project.pz.webserver.model.MetricModel;

import java.util.List;

/**
 * Created by Piotr Sołtysiak on 2016-06-13.
 * Contact: piotrek.soltysiak@gmail.com
 */
public interface MetricService {

    List<MetricModel> getMetrics(String monitorId, String sensorId) throws MonitorNotFoundException;

    MetricModel getMetric(String monitorId, String sensorId, String metricId) throws MetricNotFoundException, MonitorNotFoundException;

    MetricModel addMetric(String monitorId, String sensorId, MetricModel metric) throws MonitorNotFoundException, JsonProcessingException;

    void removeMetric(String monitorId, String sensorId, String metricId) throws MonitorException, MetricNotFoundException, MetricOwnerException;
}
