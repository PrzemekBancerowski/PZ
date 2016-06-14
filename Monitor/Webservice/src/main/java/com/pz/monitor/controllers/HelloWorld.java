package com.pz.monitor.controllers;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/hello")
public class HelloWorld extends Base {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return "Welcome on the board :)!";
    }
}