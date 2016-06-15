package com.pz.monitor.controllers;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Path("/last_measurements")
public class LastMeasurements extends Base {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response sensors() {
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

}