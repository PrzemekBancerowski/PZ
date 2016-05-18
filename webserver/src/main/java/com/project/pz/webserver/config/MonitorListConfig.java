package com.project.pz.webserver.config;

import com.project.pz.webserver.model.MonitorConfigModel;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

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
}
