package com.project.pz.webserver.service.impl;

import com.project.pz.webserver.config.MonitorListConfig;
import com.project.pz.webserver.exception.MonitorNotFoundException;
import com.project.pz.webserver.exception.MonitorNotUniqueException;
import com.project.pz.webserver.model.MonitorConfigModel;
import com.project.pz.webserver.model.MonitorDetailModel;
import com.project.pz.webserver.model.response.HostsResponse;
import com.project.pz.webserver.service.HostService;
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

    @Autowired
    private HostService hostService;

    @Override
    public List<MonitorConfigModel> getMonitors() {
        return monitorListConfig.getMonitorList();
    }

    @Override
    public MonitorDetailModel getMonitorForId(Integer monitorId) throws MonitorNotFoundException, MonitorNotUniqueException {
        HostsResponse response = hostService.getHostsForMonitor(monitorId);
        MonitorConfigModel monitorConfigModel = monitorListConfig.getMonitorById(monitorId);

        MonitorDetailModel monitorDetailModel = new MonitorDetailModel();
        monitorDetailModel.setId(monitorConfigModel.getId());
        monitorDetailModel.setAddress(monitorConfigModel.getAddress());
        monitorDetailModel.setName(monitorConfigModel.getName());
        monitorDetailModel.setHosts(response.getHosts());

        return monitorDetailModel;
    }
}
