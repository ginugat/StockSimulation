/**
 *
 */
package controller;

import beans.StockDAO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import datarepository.StockServiceDAO;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

/**
 * @author Paranjit Singh
 */
@Path("/stocks")
public class StocksService {

    @GET
    @Path("/some")
    @Produces(MediaType.TEXT_PLAIN)
    public String simple() {
        return "hello";
    }

    @Path("/getStocks")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getStocks(@Context UriInfo uriInfo) {

        String response = "";
        String tickers = uriInfo.getQueryParameters().getFirst("tickers");

        if (!"".equalsIgnoreCase(tickers)) {
            StockDAO stockDAO[] = StockServiceDAO.getStocks(tickers.split(","));

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

            try {
                response = objectMapper.writeValueAsString(stockDAO);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }

        return response;
    }
}
