package com.project.pz.webserver.service.impl;

import com.project.pz.webserver.model.MeasurementModel;
import com.project.pz.webserver.model.request.SimpleSensorDetailsRequest;
import com.project.pz.webserver.model.response.ComplexSensorDetailsResponse;
import com.project.pz.webserver.model.response.SimpleSensorDetailsResponse;
import com.project.pz.webserver.service.MeasurementService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Piotr Sołtysiak on 2016-05-06.
 * Contact: piotrek.soltysiak@gmail.com
 */
@Service
public class MeasurementServiceDefaultImpl implements MeasurementService {

    @Override
    public List<MeasurementModel> getMeasurementsForUser(Integer userId) {
        return null;
    }

    @Override
    public MeasurementModel getMeasurementById(Integer userId, Integer measurementId) {
        return null;
    }

    @Override
    public MeasurementModel createMeasurement(Integer userId, MeasurementModel measurement) {
        return null;
    }

    @Override
    public void deleteMeasurement(Integer userId, Integer measurementId) {

    }

    @Override
    public MeasurementModel updateMeasurement(Integer userId, Integer measurementId, MeasurementModel measurement) {
        return null;
    }

    @Override
    public SimpleSensorDetailsResponse getSimpleMeasurement(SimpleSensorDetailsRequest request) {
        return null; // TODO
    }

    @Override
    public ComplexSensorDetailsResponse getComplexMeasurement(Integer measurementId) {
        return null;
    }


}
