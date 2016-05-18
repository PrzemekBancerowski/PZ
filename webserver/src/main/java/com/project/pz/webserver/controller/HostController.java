package com.project.pz.webserver.controller;

import com.project.pz.webserver.exception.MonitorNotFoundException;
import com.project.pz.webserver.exception.MonitorNotUniqueException;
import com.project.pz.webserver.model.response.HostResponse;
import com.project.pz.webserver.service.HostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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
    public HostResponse getForMonitorId(@RequestHeader String token, @RequestParam Integer monitorId) throws MonitorNotFoundException, MonitorNotUniqueException {
        // todo obsluzyc token
        return hostService.getHostsForMonitor(monitorId);
    }

}
