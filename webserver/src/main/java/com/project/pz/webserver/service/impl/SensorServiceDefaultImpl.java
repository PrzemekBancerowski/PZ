package com.project.pz.webserver.service.impl;

import com.project.pz.webserver.exception.MonitorNotFoundException;
import com.project.pz.webserver.model.MonitorModel;
import com.project.pz.webserver.model.SensorModel;
import com.project.pz.webserver.service.MonitorService;
import com.project.pz.webserver.service.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

/**
 * Created by Piotr Sołtysiak on 2016-05-18.
 * Contact: piotrek.soltysiak@gmail.com
 */
@Service
public class SensorServiceDefaultImpl implements SensorService {

    @Autowired
    private MonitorService monitorService;

    @Override
    public List<SensorModel> getSensors(String monitorId) throws MonitorNotFoundException {
        RestTemplate restTemplate = new RestTemplate();
        MonitorModel monitor = monitorService.getMonitorForId(monitorId);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Accept", MediaType.ALL_VALUE);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(monitor.getAddress() + "/sensors");

        HttpEntity<?> httpEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<List<SensorModel>> responseEntity = restTemplate.exchange(
                builder.build().encode().toUri(),
                HttpMethod.GET,
                httpEntity,
                new ParameterizedTypeReference<List<SensorModel>>() {
                });

        return responseEntity.getBody();
    }
}
