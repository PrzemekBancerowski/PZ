package com.pz.monitor.controllers;

import com.pz.monitor.database.Query;
import com.pz.monitor.responses.HostsResponse;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.ResultSet;

@Path("/hosts")
public class Hosts extends Base {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response hosts() {
        try {
            Query query = db.queryFactory().hostQuery();
            ResultSet resultSet = db.execute(query);
            HostsResponse response = new HostsResponse(resultSet);

            String jsonResult = mapper.writeValueAsString(response);
            return Response.ok(jsonResult, MediaType.APPLICATION_JSON).build();

        } catch (Exception ex) {
            return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build();
        }
    }
}
