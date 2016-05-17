package com.project.pz.webserver.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Piotr Sołtysiak on 2016-05-17.
 * Contact: piotrek.soltysiak@gmail.com
 */
public class ComplexSensorDetailsResponse {

    @JsonProperty("Value")
    private Double value;

    public ComplexSensorDetailsResponse() {
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}
