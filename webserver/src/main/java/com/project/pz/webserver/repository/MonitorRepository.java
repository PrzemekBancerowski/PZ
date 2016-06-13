package com.project.pz.webserver.repository;

import com.project.pz.webserver.exception.MonitorNotFoundException;
import com.project.pz.webserver.exception.MonitorNotUniqueException;
import com.project.pz.webserver.model.MonitorModel;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Objects;

/**
 * Created by Piotr Sołtysiak on 2016-05-18.
 * Contact: piotrek.soltysiak@gmail.com
 */
@ConfigurationProperties(prefix = "monitor-configuration")
@Configuration
public class MonitorRepository {

    private List<MonitorModel> monitorList;

    public List<MonitorModel> getMonitorList() {
        return monitorList;
    }

    public void setMonitorList(List<MonitorModel> monitorConfigList) {
        this.monitorList = monitorConfigList;
    }

    public MonitorModel getMonitorById(Integer monitorId) throws MonitorNotFoundException, MonitorNotUniqueException {
        final MonitorModel[] monitorArray = {null};
        monitorList.stream()
                .filter(monitor -> Objects.equals(monitor.getId(), monitorId))
                .findFirst()
                .ifPresent(monitor -> monitorArray[0] = monitor);

        if (monitorArray[0] == null) {
            throw new MonitorNotFoundException("Monitor for id: " + monitorId + "not found");
        }

        if (monitorArray.length > 1) {
            throw new MonitorNotUniqueException("Multiple monitors found");
        }

        return monitorArray[0];
    }

}
