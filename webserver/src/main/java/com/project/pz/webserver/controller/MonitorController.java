package com.project.pz.webserver.controller;

import com.project.pz.webserver.model.MonitorConfigModel;
import com.project.pz.webserver.service.MonitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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
    public List<MonitorConfigModel> getMonitors() {
        return monitorService.getMonitors();
    }

}
