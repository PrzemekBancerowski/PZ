package com.project.pz.webserver.service.impl;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.pz.webserver.exception.MetricNotFoundException;
import com.project.pz.webserver.exception.MetricOwnerException;
import com.project.pz.webserver.exception.MonitorException;
import com.project.pz.webserver.exception.MonitorNotFoundException;
import com.project.pz.webserver.model.MetricModel;
import com.project.pz.webserver.model.MonitorModel;
import com.project.pz.webserver.model.UserModel;
import com.project.pz.webserver.service.MetricService;
import com.project.pz.webserver.service.MonitorService;
import com.project.pz.webserver.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

/**
 * Created by Piotr Sołtysiak on 2016-06-13.
 * Contact: piotrek.soltysiak@gmail.com
 */
@Service
public class MetricServiceDefaultImpl implements MetricService {

    private final Logger logger = Logger.getLogger(MetricServiceDefaultImpl.class);

//    private List<MetricModel> metricList = mockMetricList();

    @Autowired
    private MonitorService monitorService;

    @Autowired
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;


    @Override
    public List<MetricModel> getMetrics(String monitorId, String sensorId) throws MonitorNotFoundException {
        RestTemplate restTemplate = new RestTemplate();
        MonitorModel monitor = monitorService.getMonitorForId(monitorId);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Accept", MediaType.ALL_VALUE);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(monitor.getAddress() + "/sensors/" + sensorId + "/metrics");

        HttpEntity<?> httpEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<List<MetricModel>> responseEntity = restTemplate.exchange(
                builder.build().encode().toUri(),
                HttpMethod.GET,
                httpEntity,
                new ParameterizedTypeReference<List<MetricModel>>() {
                });


        return responseEntity.getBody();
    }

    @Override
    public MetricModel getMetric(String monitorId, String sensorId, String metricId) throws MetricNotFoundException, MonitorNotFoundException {

        RestTemplate restTemplate = new RestTemplate();
        MonitorModel monitor = monitorService.getMonitorForId(monitorId);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Accept", MediaType.ALL_VALUE);
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(monitor.getAddress() + "/sensors/" + sensorId + "/metrics/" + metricId);

        HttpEntity<?> httpEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<MetricModel> responseEntity = restTemplate.exchange(
                builder.build().encode().toUri(),
                HttpMethod.GET,
                httpEntity,
                MetricModel.class);

        if (responseEntity.getBody() == null || !HttpStatus.OK.equals(responseEntity.getStatusCode())) {
            throw new MetricNotFoundException(monitorId, sensorId, metricId);
        }

        return responseEntity.getBody();
    }

    @Override
    public MetricModel addMetric(String monitorId, String sensorId, MetricModel metric) throws MonitorNotFoundException, JsonProcessingException {

        RestTemplate restTemplate = new RestTemplate();
        MonitorModel monitor = monitorService.getMonitorForId(monitorId);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Accept", MediaType.ALL_VALUE);
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(monitor.getAddress() + "/sensors/" + sensorId + "/metrics");

        String jsonMetric = objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL).writeValueAsString(metric);

        HttpEntity<?> httpEntity = new HttpEntity<>(jsonMetric, httpHeaders);

        ResponseEntity<MetricModel> responseEntity = restTemplate.exchange(
                builder.build().encode().toUri(),
                HttpMethod.POST,
                httpEntity,
                MetricModel.class);

        return responseEntity.getBody();
    }

    @Override
    public void removeMetric(String monitorId, String sensorId, String metricId) throws MonitorException, MetricNotFoundException, MetricOwnerException {
        MetricModel metric = getMetric(monitorId, sensorId, metricId);
        UserModel currentUser = userService.currentUser();

        if (metric.getUserId() != null /*&& metric.getUserId().equals(currentUser.getId())*/) {
            RestTemplate restTemplate = new RestTemplate();
            MonitorModel monitor = monitorService.getMonitorForId(monitorId);

            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.set("Accept", MediaType.ALL_VALUE);

            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(monitor.getAddress() + "/sensors/" + sensorId + "/metrics/" + metricId);

            HttpEntity<?> httpEntity = new HttpEntity<>(httpHeaders);

            ResponseEntity<String> responseEntity = restTemplate.exchange(
                    builder.build().encode().toUri(),
                    HttpMethod.DELETE,
                    httpEntity,
                    String.class
            );

            if (!HttpStatus.OK.equals(responseEntity.getStatusCode())) {
                throw new MonitorException(responseEntity.getBody());
            } else {
                logger.info(responseEntity.getBody());
            }

        } else {
            throw new MetricOwnerException("Metryka nie należy do użytkownika");
        }

    }


}
