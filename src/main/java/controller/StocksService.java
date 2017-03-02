
package controller;

import beans.ProcessStock.ProcessStockData;
import beans.ProcessStock.ProcessStockRequest;
import beans.ProcessStock.ProcessStockResponse;
import beans.StockDAO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import datarepository.StockServiceDAO;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import java.util.List;
import java.util.Random;

/**
 * @author Paranjit Singh
 */
@Path("/stocks")
public class StocksService {

    @Path("/getStock")
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

    @Path("/processStock")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ProcessStockResponse postMessage(ProcessStockRequest processStockRequest) {
        List<ProcessStockData> buySellStocks = StockServiceDAO.processStocks(processStockRequest.getStock());

        ProcessStockResponse response = new ProcessStockResponse();
        response.setStock(buySellStocks);

        Random random = new Random();
        response.setTransactionID(Math.abs(random.nextInt()));

        return response;
    }
}
