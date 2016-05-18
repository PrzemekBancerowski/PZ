package com.project.pz.webserver.service.impl;

import com.project.pz.webserver.model.response.SensorMetadataResponse;
import com.project.pz.webserver.service.SensorService;
import org.springframework.stereotype.Service;

/**
 * Created by Piotr Sołtysiak on 2016-05-18.
 * Contact: piotrek.soltysiak@gmail.com
 */
@Service
public class SensorServiceDefaultImpl implements SensorService {

    @Override
    public SensorMetadataResponse getSensor(Integer sensorId) {
        return null;
    }
}
