package com.pz.monitor.controllers;

import com.pz.monitor.database.Query;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/hello")
public class HelloWorld extends Base {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return "Welcome on the board :)!";
    }

    @GET
    @Path("/reset")
    @Produces(MediaType.TEXT_PLAIN)
    public Response resetDB() {
        try {
            Query query = db.queryFactory().resetDb();
            db.execute(query);
            return Response.ok("Reset done", MediaType.TEXT_PLAIN).build();

        } catch (Exception ex) {
            return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build();
        }
    }
}