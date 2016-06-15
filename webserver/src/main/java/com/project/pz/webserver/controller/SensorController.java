package com.project.pz.webserver.controller;

import com.project.pz.webserver.model.SensorModel;
import com.project.pz.webserver.service.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Piotr Sołtysiak on 2016-05-17.
 * Contact: piotrek.soltysiak@gmail.com
 */
@RestController
@RequestMapping("/")
public class SensorController {

    @Autowired
    private SensorService sensorService;

    @RequestMapping(value = "monitors/{monitorId}/sensors", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public List<SensorModel> getSensors(@PathVariable String monitorId) {
        return sensorService.getSensors(monitorId);
    }
}
