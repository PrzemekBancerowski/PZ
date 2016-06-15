package com.pz.monitor.controllers;

import com.pz.monitor.database.Query;
import com.pz.monitor.requests.MetricRequest;
import com.pz.monitor.responses.MetricResponse;
import com.pz.monitor.responses.SensorDetailsResponse;
import com.pz.monitor.responses.SensorsResponse;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Path("/sensors")
public class Sensors extends Base {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response sensors() {
        try {
            Query query = db.queryFactory().sensorsQuery();
            ResultSet resultSet = db.execute(query);
            SensorsResponse response = new SensorsResponse(resultSet);

            String jsonResult = mapper.writeValueAsString(response.getResponse());
            return Response.ok(jsonResult, MediaType.APPLICATION_JSON).build();

        } catch (Exception ex) {
            return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build();
        }
    }

    @GET
    @Path("{sensorId}/metrics")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSensorMetrics(@PathParam("sensorId") String sensorId) {
        try {
            List<MetricResponse> metricResponses = new ArrayList<>();
            for (int i=0; i<10; i++) {
                metricResponses.add( new MetricResponse());
            }
            String jsonResult = mapper.writeValueAsString(metricResponses);
            return Response.ok(jsonResult, MediaType.APPLICATION_JSON).build();

        } catch (Exception ex) {
            return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build();
        }
    }

    @POST
    @Path("{sensorId}/metrics")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postSensorMetrics(MetricRequest request) {
        try {
            MetricResponse response = new MetricResponse(request);
            String result = mapper.writeValueAsString(response);
            return Response.ok(result, MediaType.TEXT_PLAIN).build();

        } catch (Exception ex) {
            return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build();
        }
    }

    @GET
    @Path("{sensorId}/metrics/{metricId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMetric() {
        try {
            MetricResponse response = new MetricResponse();
            String jsonResult = mapper.writeValueAsString(response);
            return Response.ok(jsonResult, MediaType.APPLICATION_JSON).build();

        } catch (Exception ex) {
            return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build();
        }
    }

    @DELETE
    @Path("{sensorId}/metrics/{metricId}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response postMetric(@PathParam("sensorId") String sensorId, @PathParam("metricId") String metricId) {
        try {
            return Response.ok("Deleted "+ sensorId + " sensor's " + metricId + " metric", MediaType.TEXT_PLAIN).build();
        } catch (Exception ex) {
            return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build();
        }
    }

    @GET
    @Path("{sensorId}/metrics/{metricId}/measurements")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMeasurement() {
        try {
            Random gen = new Random();
            List<Double> lastMeasurements = new ArrayList<>();
            for (int i=0; i<50; i++) {
                lastMeasurements.add(Math.abs(gen.nextDouble()));
            }

            String jsonResult = mapper.writeValueAsString(lastMeasurements);
            return Response.ok(jsonResult, MediaType.APPLICATION_JSON).build();

        } catch (Exception ex) {
            return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build();
        }
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response host(@PathParam("id") String id) {
        try {
            Query query = db.queryFactory().sensorQuery(id);
            ResultSet resultSet = db.execute(query);
            SensorDetailsResponse response = new SensorDetailsResponse(resultSet);

            String jsonResult = mapper.writeValueAsString(response);
            return Response.ok(jsonResult, MediaType.APPLICATION_JSON).build();

        } catch (Exception ex) {
            return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build();
        }
    }
}
