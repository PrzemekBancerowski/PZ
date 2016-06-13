package com.project.pz.webserver.service.impl;

import com.google.common.collect.Lists;
import com.project.pz.webserver.service.MeasurementService;
import com.project.pz.webserver.service.MonitorService;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
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
    public List<BigDecimal> getMeasurements(String monitorId, String sensorId, String metricId, LocalDateTime fromDate, LocalDateTime toDate) {
        return mockMeasurements();
    }

    private List<BigDecimal> mockMeasurements() {
        List<BigDecimal> measurementList = Lists.newArrayList();

        for (int i=0; i<10; i++) {
            measurementList.add(new BigDecimal(RandomUtils.nextDouble(0.0, 9999.99)));
        }

        return measurementList;
    }

//    public SimpleSensorDetailsResponse getSimpleMeasurement(SimpleSensorDetailsRequest request) throws MonitorNotFoundException, MonitorNotUniqueException {
//
//        RestTemplate restTemplate = new RestTemplate();
//        MonitorModel monitor = new MonitorModel(); // TODO
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.set("Accept", MediaType.ALL_VALUE);
//
//        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(monitor.getAddress()+"/sensorDetails/simple")
//                .queryParam("SensorId", request.getSensorId())
//                .queryParam("Measurement", request.getMeasurement().name())
//                .queryParam("StartTime", request.getStartTime())
//                .queryParam("EndTime", request.getEndTime())
//                .queryParam("MaxCount", request.getMaxCount());
//
//        HttpEntity<?> httpEntity = new HttpEntity<>(headers);
//
//        ResponseEntity<SimpleSensorDetailsResponse> responseEntity = restTemplate.exchange(
//                builder.build().encode().toUri(),
//                HttpMethod.GET,
//                httpEntity,
//                SimpleSensorDetailsResponse.class);
//
//        return responseEntity.getBody();
//    }


}
