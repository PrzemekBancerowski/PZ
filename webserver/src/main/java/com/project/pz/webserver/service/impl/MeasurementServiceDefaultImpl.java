package com.project.pz.webserver.service.impl;

import com.project.pz.webserver.exception.MonitorNotFoundException;
import com.project.pz.webserver.model.MonitorModel;
import com.project.pz.webserver.service.MeasurementService;
import com.project.pz.webserver.service.MonitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Piotr Sołtysiak on 2016-05-06.
 * Contact: piotrek.soltysiak@gmail.com
 */
@Service
public class MeasurementServiceDefaultImpl implements MeasurementService {

    @Autowired
    private MonitorService monitorService;

    @Override
    public List<BigDecimal> getMeasurements(String monitorId, String sensorId, String metricId, Long fromTime, Long toTime) throws MonitorNotFoundException {

        RestTemplate restTemplate = new RestTemplate();
        MonitorModel monitor = monitorService.getMonitorForId(monitorId);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Accept", MediaType.ALL_VALUE);
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(monitor.getAddress() + "/sensors/" + sensorId + "/metrics/" + metricId + "/measurements");

        HttpEntity<?> httpEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<List<BigDecimal>> responseEntity = restTemplate.exchange(
                builder.build().encode().toUri(),
                HttpMethod.GET,
                httpEntity,
                new ParameterizedTypeReference<List<BigDecimal>>() {});


        return responseEntity.getBody();
    }


}
