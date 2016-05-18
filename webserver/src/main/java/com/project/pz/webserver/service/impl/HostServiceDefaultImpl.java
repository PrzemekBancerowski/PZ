package com.project.pz.webserver.service.impl;

import com.project.pz.webserver.config.MonitorListConfig;
import com.project.pz.webserver.exception.MonitorNotFoundException;
import com.project.pz.webserver.exception.MonitorNotUniqueException;
import com.project.pz.webserver.model.response.HostsResponse;
import com.project.pz.webserver.service.HostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Piotr Sołtysiak on 2016-05-17.
 * Contact: piotrek.soltysiak@gmail.com
 */
@Service
public class HostServiceDefaultImpl implements HostService {

    @Autowired
    private MonitorListConfig monitorListConfig;

    @Override
    public HostsResponse getHostsForMonitor(Integer monitorId) throws MonitorNotFoundException, MonitorNotUniqueException {
        String address = monitorListConfig.getMonitorById(monitorId).getAddress();

        RestTemplate restTemplate = new RestTemplate();

        return restTemplate.getForObject(address+"/hosts", HostsResponse.class);
    }
}
