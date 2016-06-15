package com.project.pz.webserver.service.impl;

import com.project.pz.webserver.exception.MonitorNotFoundException;
import com.project.pz.webserver.model.MonitorModel;
import com.project.pz.webserver.repository.MonitorRepository;
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
    private MonitorRepository monitorRepository;

    @Override
    public List<MonitorModel> getMonitors() {
        return monitorRepository.getMonitorList();
    }

    public MonitorModel getMonitorForId(String monitorId) throws MonitorNotFoundException {
        for (MonitorModel monitor: getMonitors()) {
            if (monitor.getId() != null && monitor.getId().equals(monitorId)) {
                return monitor;
            }
        }

        throw new MonitorNotFoundException("Nie znaleziono monitora o ID: "+monitorId);
    }
}
