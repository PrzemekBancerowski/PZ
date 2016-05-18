package com.project.pz.webserver.service;

import com.project.pz.webserver.AbstractTest;
import com.project.pz.webserver.dict.Measure;
import com.project.pz.webserver.exception.MonitorNotFoundException;
import com.project.pz.webserver.exception.MonitorNotUniqueException;
import com.project.pz.webserver.model.request.SimpleSensorDetailsRequest;
import com.project.pz.webserver.model.response.SimpleSensorDetailsResponse;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Piotr Sołtysiak on 2016-05-18.
 * Contact: piotrek.soltysiak@gmail.com
 */
public class MeasurementServiceTest extends AbstractTest {

    @Autowired
    private MeasurementService measurementService;

    @Test
    public void getSimpleMeasurementTest() throws MonitorNotFoundException, MonitorNotUniqueException {
        SimpleSensorDetailsRequest request = new SimpleSensorDetailsRequest("sensor6", Measure.CPU, 0L, 100000000000L, 100);

        SimpleSensorDetailsResponse response = measurementService.getSimpleMeasurement(request);
        assertNotNull(response);
        assertNotNull(response.getValues());
        assertFalse(response.getValues().isEmpty());

    }
}
