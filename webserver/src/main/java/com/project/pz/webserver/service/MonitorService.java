package com.project.pz.webserver.service;

import com.project.pz.webserver.exception.MonitorNotFoundException;
import com.project.pz.webserver.exception.MonitorNotUniqueException;
import com.project.pz.webserver.model.MonitorSimpleModel;
import com.project.pz.webserver.model.MonitorDetailModel;

import java.util.List;

/**
 * Created by Piotr Sołtysiak on 2016-05-18.
 * Contact: piotrek.soltysiak@gmail.com
 */
public interface MonitorService {

    List<MonitorSimpleModel> getMonitors();

    MonitorDetailModel getMonitorForId(Integer monitorId) throws MonitorNotFoundException, MonitorNotUniqueException;

    MonitorSimpleModel getMonitorForSensorId(String sensorId) throws MonitorNotFoundException, MonitorNotUniqueException;
}
