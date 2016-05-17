package com.project.pz.webserver.service.impl;

import com.project.pz.webserver.model.response.HostsResponse;
import com.project.pz.webserver.service.HostService;
import org.springframework.stereotype.Service;

/**
 * Created by Piotr Sołtysiak on 2016-05-17.
 * Contact: piotrek.soltysiak@gmail.com
 */
@Service
public class HostServiceDefaultImpl implements HostService {

    @Override
    public HostsResponse getHosts() {
        return null; // TODO
    }
}
