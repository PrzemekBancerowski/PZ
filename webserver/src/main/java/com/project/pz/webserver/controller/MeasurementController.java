package com.project.pz.webserver.controller;

import com.project.pz.webserver.service.MeasurementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Piotr Sołtysiak on 2016-05-09.
 * Contact: piotrek.soltysiak@gmail.com
 */
@RestController
@RequestMapping("/")
public class MeasurementController {

    @Autowired
    private MeasurementService measurementService;

    @RequestMapping(value = "monitors/{monitorId}/sensors/{sensorId}/metrics/{metricId}/measurements", method = RequestMethod.GET)
    public List<BigDecimal> getMeasurements(@PathVariable String monitorId, @PathVariable String sensorId, @PathVariable String metricId,
                                            @RequestParam(value = "fromDate", required = false) LocalDateTime fromDate,
                                            @RequestParam(value = "toDate", required = false) LocalDateTime toDate) {
        return measurementService.getMeasurements(monitorId, sensorId, metricId, fromDate, toDate);
    }

}
