package com.pz.monitor.requests;

import javax.ws.rs.QueryParam;

public class MetricRequest implements Request {
    @QueryParam("metricId")
    public String id = "metricId";

    @QueryParam("description")
    public String description = "metric desc";

    @QueryParam("metricType")
    public String metricType = "metric type";

    @QueryParam("measure")
    public String measure = "metric measure";

    @QueryParam("userId")
    public String userId = "user id ";

    @QueryParam("interval")
    public Integer interval = 100;

    @QueryParam("windowSize")
    public Integer windowSize = 230;


    @Override
    public void reassignValues() {
    }

    @Override
    public void validate() {
    }

}
