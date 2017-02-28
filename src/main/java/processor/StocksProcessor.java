/**
 * 
 */
package processor;

import beans.RawStock;
import beans.StockDAO;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import constants.StocksContants;
import datarepository.StockServiceDAO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author Paranjit Singh
 *
 */
public class StocksProcessor {

	public void pool() {

		try {

			URL url = new URL(StocksContants.URL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			StringBuilder builder = new StringBuilder();
			String output;

			while (br.ready() && (output = br.readLine()) != null) {
				builder.append(output);
			}

			conn.disconnect();

			ObjectMapper mapper = new ObjectMapper();

			int index = builder.indexOf("//");
			builder.replace(index, index + 2, "");

			RawStock rawStock[] = mapper.readValue(builder.toString(), RawStock[].class);

			for (int i = 0; i < rawStock.length; i++) {
				int counter = StockServiceDAO.getCounterValue(rawStock[i].getT());

				String price = rawStock[i].getL().charAt(0) == '+' ? rawStock[i].getL().substring(1)
						: rawStock[i].getL();
				String change_amount = rawStock[i].getC().charAt(0) == '+' ? rawStock[i].getC().substring(1)
						: rawStock[i].getC();
				String change_percent = rawStock[i].getCp().charAt(0) == '+' ? rawStock[i].getCp().substring(1)
						: rawStock[i].getCp();

				price = price.replace(",", "");

				StockDAO stockDAO = new StockDAO(rawStock[i].getT(), counter + 1, Float.parseFloat(price),
						Float.parseFloat(change_amount), Float.parseFloat(change_percent));

				if (counter == 0) {
					// Insert Stocks
					StockServiceDAO.saveStock(stockDAO);
				} else {
					// Update Stocks
					StockServiceDAO.updateStock(stockDAO);
				}
			}

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();

		}

	}

}
