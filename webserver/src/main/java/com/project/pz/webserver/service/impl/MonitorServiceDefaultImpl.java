package com.project.pz.webserver.service.impl;

import com.project.pz.webserver.exception.MonitorNotFoundException;
import com.project.pz.webserver.exception.MonitorNotUniqueException;
import com.project.pz.webserver.model.MonitorDetailModel;
import com.project.pz.webserver.model.MonitorSimpleModel;
import com.project.pz.webserver.model.response.HostResponse;
import com.project.pz.webserver.repository.MonitorRepository;
import com.project.pz.webserver.service.HostService;
import com.project.pz.webserver.service.MonitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Piotr Sołtysiak on 2016-05-18.
 * Contact: piotrek.soltysiak@gmail.com
 */
@Service
public class MonitorServiceDefaultImpl implements MonitorService {

    @Autowired
    private MonitorRepository monitorRepository;

    @Autowired
    private HostService hostService;

    @Override
    public List<MonitorSimpleModel> getMonitors() {
        return monitorRepository.getMonitorList();
    }

    @Override
    public MonitorDetailModel getMonitorForId(Integer monitorId) throws MonitorNotFoundException, MonitorNotUniqueException {
        HostResponse response = hostService.getHostsForMonitor(monitorId);
        MonitorSimpleModel monitorSimpleModel = monitorRepository.getMonitorById(monitorId);

        MonitorDetailModel monitorDetailModel = new MonitorDetailModel();
        monitorDetailModel.setId(monitorSimpleModel.getId());
        monitorDetailModel.setAddress(monitorSimpleModel.getAddress());
        monitorDetailModel.setName(monitorSimpleModel.getName());
        monitorDetailModel.setHosts(response.getHosts());

        return monitorDetailModel;
    }

    @Override
    public MonitorSimpleModel getMonitorForSensorId(String sensorId) throws MonitorNotFoundException, MonitorNotUniqueException {

        for (MonitorSimpleModel monitorSimpleModel : monitorRepository.getMonitorList()) {
            HostResponse response = hostService.getHostsForMonitor(monitorSimpleModel.getId());

            for (Map.Entry<String, List<String>> host : response.getHosts().entrySet()) {
                for (String sId : host.getValue()) {
                    if (sId.equals(sensorId)) {
                        return monitorSimpleModel;
                    }
                }
            }

        }

        throw new MonitorNotFoundException("Monitor not found for sensor with id: " + sensorId);
    }
}
