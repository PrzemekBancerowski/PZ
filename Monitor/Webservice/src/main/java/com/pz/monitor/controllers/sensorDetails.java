package com.pz.monitor.controllers;

import com.pz.monitor.database.Query;
import com.pz.monitor.requests.ComplexSensorDetailsRequest;
import com.pz.monitor.requests.SimpleSensorDetailsRequest;
import com.pz.monitor.responses.ComplexSensorDetailsResponse;
import com.pz.monitor.responses.SimpleSensorDetailsResponse;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.ResultSet;

@Path("/sensorDetails")
public class SensorDetails extends Base {

    @POST
    @Path("/complex")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getComplex(ComplexSensorDetailsRequest request) {
        try {
            request.validate();
            request.reassignValues();

            Query query = db.queryFactory().fromRequest(request);
            ResultSet resultSet = db.execute(query);
            ComplexSensorDetailsResponse response = new ComplexSensorDetailsResponse(resultSet);

            String jsonResult = mapper.writeValueAsString(response);
            return Response.ok(jsonResult, MediaType.APPLICATION_JSON).build();
        } catch (Exception ex) {
            return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build();
        }
    }


    @GET
    @Path("/simple")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSimple(@BeanParam SimpleSensorDetailsRequest request) {
        try {
            request.validate();
            request.reassignValues();

            Query query = db.queryFactory().fromRequest(request);
            ResultSet resultSet = db.execute(query);
            SimpleSensorDetailsResponse response = new SimpleSensorDetailsResponse(resultSet);

            String jsonResult = mapper.writeValueAsString(response);
            return Response.ok(jsonResult, MediaType.APPLICATION_JSON).build();
        } catch (Exception ex) {
            return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build();
        }
    }
}
