package com.pz.monitor.controllers;

import com.pz.monitor.database.Query;
import com.pz.monitor.responses.SensorDetailsResponse;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.ResultSet;

@Path("/sensors")
public class Sensors extends Base {

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
