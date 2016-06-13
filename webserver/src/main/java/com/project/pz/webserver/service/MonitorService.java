package com.project.pz.webserver.service;

import com.project.pz.webserver.model.MonitorSimpleModel;

import java.util.List;

/**
 * Created by Piotr Sołtysiak on 2016-05-18.
 * Contact: piotrek.soltysiak@gmail.com
 */
public interface MonitorService {

    List<MonitorSimpleModel> getMonitors();

}
