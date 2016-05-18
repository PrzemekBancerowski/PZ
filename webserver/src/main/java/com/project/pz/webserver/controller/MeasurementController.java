package com.project.pz.webserver.controller;

import com.project.pz.webserver.model.MeasurementModel;
import com.project.pz.webserver.model.request.SimpleSensorDetailsRequest;
import com.project.pz.webserver.model.response.ComplexSensorDetailsResponse;
import com.project.pz.webserver.model.response.SimpleSensorDetailsResponse;
import com.project.pz.webserver.service.MeasurementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Piotr Sołtysiak on 2016-05-09.
 * Contact: piotrek.soltysiak@gmail.com
 */
@RestController
@RequestMapping("/measurements")
public class MeasurementController {

    @Autowired
    private MeasurementService measurementService;

    @RequestMapping(method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public List<MeasurementModel> getMeasurementsForUser(@RequestHeader String token) {
        // todo obsłużyć token
        return measurementService.getMeasurementsForUser(1);
    }

//    @RequestMapping(method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
//    @ResponseStatus(HttpStatus.OK)
//    public MeasurementModel getMeasurementById(@RequestHeader String token, @RequestParam Integer measurementId) {
//        // todo obsłużyć token
//        return measurementService.getMeasurementById(1, measurementId);
//    }

    @RequestMapping(method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public MeasurementModel createMeasurement(@RequestHeader String token, @RequestBody MeasurementModel measurement) {
        // todo obsłużyć token
        return measurementService.createMeasurement(1, measurement);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteMeasurement(@RequestHeader String token, @RequestBody Integer measurementId) {
        // todo obsłużyć token
        measurementService.deleteMeasurement(1, measurementId);
    }

    @RequestMapping(method = RequestMethod.PUT, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public MeasurementModel updateMeasurement(@RequestHeader String token, @RequestParam Integer measurementId, @RequestBody MeasurementModel measurement) {
        // todo obsłużyć token
        return measurementService.updateMeasurement(1, measurementId, measurement);
    }

    @RequestMapping(path = "/simple", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public SimpleSensorDetailsResponse getSimpleMeasurement(String SensorId, String Measurement, Integer MaxCount, Long StartTime, Long EndTime) {
        SimpleSensorDetailsRequest simpleSensorDetailsRequest = new SimpleSensorDetailsRequest(SensorId, Measurement, StartTime, EndTime, MaxCount);
        return measurementService.getSimpleMeasurement(simpleSensorDetailsRequest);
    }

    @RequestMapping(path = "/complex", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public ComplexSensorDetailsResponse getComplexMeasurement(@RequestHeader String token, @RequestParam Integer measurementId) {
        // todo obsłużyć token
        return measurementService.getComplexMeasurement(measurementId);
    }


}
