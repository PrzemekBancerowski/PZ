package com.project.pz.webserver.service.impl;

import com.project.pz.webserver.config.MonitorListConfig;
import com.project.pz.webserver.model.MonitorConfigModel;
import com.project.pz.webserver.service.MonitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Piotr Sołtysiak on 2016-05-18.
 * Contact: piotrek.soltysiak@gmail.com
 */
@Service
public class MonitorServiceDefaultImpl implements MonitorService {

    @Autowired
    private MonitorListConfig monitorListConfig;

    @Override
    public List<MonitorConfigModel> getMonitors() {
        return monitorListConfig.getMonitorConfigList();
    }
}
