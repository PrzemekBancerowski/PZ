package com.project.pz.webserver.service;

import com.project.pz.webserver.model.response.SensorMetadataResponse;

/**
 * Created by Piotr Sołtysiak on 2016-05-17.
 * Contact: piotrek.soltysiak@gmail.com
 */
public interface SensorService {

    SensorMetadataResponse getSensor(Integer sensorId);
}
