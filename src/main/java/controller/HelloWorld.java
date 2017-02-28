/* Copyright 2017 Amazon.com, Inc. or its affiliates. All Rights Reserved. */
package controller;

import beans.Stock;
import beans.StockDAO;
import datarepository.StockServiceDAO;

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
    @Produces(MediaType.APPLICATION_JSON)
    public List<Stock> postMessage(final List<Stock> stocks) {

        int list_size = stocks.size();
        String[] ids = new String[list_size];
        StockDAO[] outputStocks = new StockDAO[list_size];
        String id;
        long quantity;
        int counter;
        for (int i = 0; i < list_size; i++) {
            id = stocks.get(i).getId();
            //quantity = stocks.get(i).getQuantity();
            //counter = stocks.get(i).getCounter();
            ids[i] = id;
        }
        System.out.println(StockServiceDAO.buysellStocks(ids));
        return StockServiceDAO.buysellStocks(ids);

    }
}