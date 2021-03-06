package com.project.pz.webserver.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.project.pz.webserver.exception.MetricNotFoundException;
import com.project.pz.webserver.exception.MetricOwnerException;
import com.project.pz.webserver.exception.MonitorException;
import com.project.pz.webserver.exception.MonitorNotFoundException;
import com.project.pz.webserver.model.MetricModel;
import com.project.pz.webserver.service.MetricService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Piotr Sołtysiak on 2016-06-13.
 * Contact: piotrek.soltysiak@gmail.com
 */
@RestController
@RequestMapping("/")
public class MetricController {

    @Autowired
    private MetricService metricService;

    @RequestMapping(value = "monitors/{monitorId}/sensors/{sensorId}/metrics", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody List<MetricModel> getMetrics(@PathVariable String monitorId, @PathVariable String sensorId) throws MonitorNotFoundException {
        return metricService.getMetrics(monitorId, sensorId);
    }

    @RequestMapping(value = "monitors/{monitorId}/sensors/{sensorId}/metrics/{metricId}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public MetricModel getMetric(@PathVariable String monitorId, @PathVariable String sensorId, @PathVariable String metricId) throws MetricNotFoundException, MonitorNotFoundException {
        return metricService.getMetric(monitorId, sensorId, metricId);
    }

    @RequestMapping(value = "monitors/{monitorId}/sensors/{sensorId}/metrics", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public MetricModel addMetric(@PathVariable String monitorId, @PathVariable String sensorId, @RequestBody MetricModel metric) throws MonitorNotFoundException, JsonProcessingException {
        return metricService.addMetric(monitorId, sensorId, metric);
    }

    @RequestMapping(value = "monitors/{monitorId}/sensors/{sensorId}/metrics/{metricId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void removeMetric(@PathVariable String monitorId, @PathVariable String sensorId, @PathVariable String metricId) throws MetricNotFoundException, MonitorException, MetricOwnerException {
        metricService.removeMetric(monitorId, sensorId, metricId);
    }


}
