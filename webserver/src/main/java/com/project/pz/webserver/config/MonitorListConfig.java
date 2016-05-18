package com.project.pz.webserver.config;

import com.project.pz.webserver.exception.MonitorNotFoundException;
import com.project.pz.webserver.exception.MonitorNotUniqueException;
import com.project.pz.webserver.model.MonitorConfigModel;
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
public class MonitorListConfig {

    private List<MonitorConfigModel> monitorList;

    public List<MonitorConfigModel> getMonitorList() {
        return monitorList;
    }

    public void setMonitorList(List<MonitorConfigModel> monitorConfigList) {
        this.monitorList = monitorConfigList;
    }

    public MonitorConfigModel getMonitorById(Integer monitorId) throws MonitorNotFoundException, MonitorNotUniqueException {
        final MonitorConfigModel[] monitorArray = {null};
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
