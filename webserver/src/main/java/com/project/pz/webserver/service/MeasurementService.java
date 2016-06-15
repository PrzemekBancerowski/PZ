package com.project.pz.webserver.service;

import com.project.pz.webserver.exception.MonitorNotFoundException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Piotr Sołtysiak on 2016-05-06.
 * Contact: piotrek.soltysiak@gmail.com
 */
public interface MeasurementService {

    List<BigDecimal> getMeasurements(String monitorId, String sensorId, String metricId, Long fromTime, Long toTime) throws MonitorNotFoundException;

}
