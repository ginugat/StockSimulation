/* Copyright 2017 Amazon.com, Inc. or its affiliates. All Rights Reserved. */
package controller;

import beans.Stock;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/hello")
public class HelloWorld {
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getMessage() {
        return "Hello world!";
    }

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public String postMessage(final List<Stock> stocks) {
        return String.valueOf(stocks.get(0).getId());
    }
}