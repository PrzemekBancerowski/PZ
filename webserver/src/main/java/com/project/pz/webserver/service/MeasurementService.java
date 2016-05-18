package com.project.pz.webserver.service;

import com.project.pz.webserver.exception.MonitorNotFoundException;
import com.project.pz.webserver.exception.MonitorNotUniqueException;
import com.project.pz.webserver.model.MeasurementModel;
import com.project.pz.webserver.model.request.SimpleSensorDetailsRequest;
import com.project.pz.webserver.model.response.ComplexSensorDetailsResponse;
import com.project.pz.webserver.model.response.SimpleSensorDetailsResponse;

import java.util.List;

/**
 * Created by Piotr Sołtysiak on 2016-05-06.
 * Contact: piotrek.soltysiak@gmail.com
 */
public interface MeasurementService {

    List<MeasurementModel> getMeasurementsForUser(Integer userId);

    MeasurementModel getMeasurementById(Integer userId, Integer measurementId);

    MeasurementModel createMeasurement(Integer userId, MeasurementModel measurement);

    void deleteMeasurement(Integer userId, Integer measurementId);

    MeasurementModel updateMeasurement(Integer userId, Integer measurementId, MeasurementModel measurement);

    SimpleSensorDetailsResponse getSimpleMeasurement(SimpleSensorDetailsRequest request) throws MonitorNotFoundException, MonitorNotUniqueException;

    ComplexSensorDetailsResponse getComplexMeasurement(Integer measurementId);
}
