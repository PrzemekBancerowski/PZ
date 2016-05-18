package com.project.pz.webserver.service.impl;

import com.project.pz.webserver.exception.MonitorNotFoundException;
import com.project.pz.webserver.exception.MonitorNotUniqueException;
import com.project.pz.webserver.model.MeasurementModel;
import com.project.pz.webserver.model.MonitorSimpleModel;
import com.project.pz.webserver.model.request.SimpleSensorDetailsRequest;
import com.project.pz.webserver.model.response.ComplexSensorDetailsResponse;
import com.project.pz.webserver.model.response.SimpleSensorDetailsResponse;
import com.project.pz.webserver.service.HostService;
import com.project.pz.webserver.service.MeasurementService;
import com.project.pz.webserver.service.MonitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

/**
 * Created by Piotr Sołtysiak on 2016-05-06.
 * Contact: piotrek.soltysiak@gmail.com
 */
@Service
public class MeasurementServiceDefaultImpl implements MeasurementService {

    @Autowired
    private HostService hostService;

    @Autowired
    private MonitorService monitorService;

    @Override
    public List<MeasurementModel> getMeasurementsForUser(Integer userId) {
        return null;
    }

    @Override
    public MeasurementModel getMeasurementById(Integer userId, Integer measurementId) {
        return null;
    }

    @Override
    public MeasurementModel createMeasurement(Integer userId, MeasurementModel measurement) {
        return null;
    }

    @Override
    public void deleteMeasurement(Integer userId, Integer measurementId) {

    }

    @Override
    public MeasurementModel updateMeasurement(Integer userId, Integer measurementId, MeasurementModel measurement) {
        return null;
    }

    @Override
    public SimpleSensorDetailsResponse getSimpleMeasurement(SimpleSensorDetailsRequest request) throws MonitorNotFoundException, MonitorNotUniqueException {

        RestTemplate restTemplate = new RestTemplate();
        MonitorSimpleModel monitor = monitorService.getMonitorForSensorId(request.sensorId);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.ALL_VALUE);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(monitor.getAddress()+"/sensorDetails/simple")
                .queryParam("SensorId", request.getSensorId())
                .queryParam("Measurement", request.getMeasurement().name())
                .queryParam("StartTime", request.getStartTime())
                .queryParam("EndTime", request.getEndTime())
                .queryParam("MaxCount", request.getMaxCount());

        HttpEntity<?> httpEntity = new HttpEntity<>(headers);

        ResponseEntity<SimpleSensorDetailsResponse> responseEntity = restTemplate.exchange(
                builder.build().encode().toUri(),
                HttpMethod.GET,
                httpEntity,
                SimpleSensorDetailsResponse.class);

        return responseEntity.getBody();
    }

    @Override
    public ComplexSensorDetailsResponse getComplexMeasurement(Integer measurementId) {
        return null;
    }


}
