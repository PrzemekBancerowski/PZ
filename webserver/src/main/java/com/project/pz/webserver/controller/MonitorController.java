package com.project.pz.webserver.controller;

import com.project.pz.webserver.exception.MonitorNotFoundException;
import com.project.pz.webserver.exception.MonitorNotUniqueException;
import com.project.pz.webserver.model.MonitorDetailModel;
import com.project.pz.webserver.model.MonitorSimpleModel;
import com.project.pz.webserver.service.MonitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Piotr Sołtysiak on 2016-05-18.
 * Contact: piotrek.soltysiak@gmail.com
 */
@RestController
@RequestMapping("/monitors")
public class MonitorController {

    @Autowired
    private MonitorService monitorService;

    @RequestMapping(method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public List<MonitorSimpleModel> getMonitors() {
        return monitorService.getMonitors();
    }

    @RequestMapping(path = "/", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public MonitorDetailModel getMonitorForId(@RequestParam Integer monitorId) throws MonitorNotFoundException, MonitorNotUniqueException {
        return monitorService.getMonitorForId(monitorId);
    }

}
