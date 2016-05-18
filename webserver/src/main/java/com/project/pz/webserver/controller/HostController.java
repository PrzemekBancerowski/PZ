package com.project.pz.webserver.controller;

import com.project.pz.webserver.exception.MonitorNotFoundException;
import com.project.pz.webserver.exception.MonitorNotUniqueException;
import com.project.pz.webserver.model.response.HostsResponse;
import com.project.pz.webserver.service.HostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Piotr Sołtysiak on 2016-05-17.
 * Contact: piotrek.soltysiak@gmail.com
 */
@RestController
@RequestMapping("/hosts")
public class HostController {

    @Autowired
    private HostService hostService;

    @RequestMapping(method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public HostsResponse get() throws MonitorNotFoundException, MonitorNotUniqueException {
        return hostService.getHosts(1);
    }

}
