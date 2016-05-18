package com.project.pz.webserver.service.impl;

import com.project.pz.webserver.repository.MonitorRepository;
import com.project.pz.webserver.exception.MonitorNotFoundException;
import com.project.pz.webserver.exception.MonitorNotUniqueException;
import com.project.pz.webserver.model.response.HostResponse;
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
    private MonitorRepository monitorRepository;

    @Override
    public HostResponse getHostsForMonitor(Integer monitorId) throws MonitorNotFoundException, MonitorNotUniqueException {
        String address = monitorRepository.getMonitorById(monitorId).getAddress();

        RestTemplate restTemplate = new RestTemplate();

        return restTemplate.getForObject(address+"/hosts", HostResponse.class);
    }
}
