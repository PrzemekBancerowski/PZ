package com.project.pz.webserver.config;

import com.project.pz.webserver.model.MonitorConfigModel;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * Created by Piotr Sołtysiak on 2016-05-18.
 * Contact: piotrek.soltysiak@gmail.com
 */
@ConfigurationProperties(prefix = "monitor-list")
@Configuration
public class MonitorListConfig {

    private List<MonitorConfigModel> monitorConfigList;

    public List<MonitorConfigModel> getMonitorConfigList() {
        return monitorConfigList;
    }

    public void setMonitorConfigList(List<MonitorConfigModel> monitorConfigList) {
        this.monitorConfigList = monitorConfigList;
    }
}
