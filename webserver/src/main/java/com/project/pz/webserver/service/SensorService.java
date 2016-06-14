package com.project.pz.webserver.service;

import com.project.pz.webserver.model.SensorModel;

import java.util.List;

/**
 * Created by Piotr Sołtysiak on 2016-05-17.
 * Contact: piotrek.soltysiak@gmail.com
 */
public interface SensorService {

    List<SensorModel> getSensors(String monitorId);

}
