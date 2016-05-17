package com.project.pz.webserver.model.response;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created by Piotr Sołtysiak on 2016-05-17.
 * Contact: piotrek.soltysiak@gmail.com
 */
public class SimpleSensorDetailsResponse {

    List<Integer> values;

    public SimpleSensorDetailsResponse() {
        values = Lists.newArrayList();
    }

    public List<Integer> getValues() {
        return values;
    }

    public void setValues(List<Integer> values) {
        this.values = values;
    }
}
