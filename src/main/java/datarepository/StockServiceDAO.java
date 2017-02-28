/**
 * 
 */
package datarepository;


import beans.StockDAO;
import constants.StocksContants;

import java.sql.*;

/**
 * @author Paranjit Singh
 *
 */
public class StockServiceDAO {

	public static int getCounterValue(String ticker) {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int counter = 0;
		try {

			connection = getConnection();
			preparedStatement = connection.prepareStatement(StocksContants.GET_COUNTER_QUERY);

			preparedStatement.setString(1, ticker);

			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				counter = resultSet.getInt(StocksContants.COUNTER);
			}

			if (preparedStatement != null) {
				preparedStatement.close();
			}

			if (connection != null) {
				connection.close();
			}

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		}

		return counter;
	}

	public static void saveStock(StockDAO stockDAO) {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {

			connection = getConnection();
			preparedStatement = connection.prepareStatement(StocksContants.INSERT_STOCK_QUERY);

			preparedStatement.setString(1, stockDAO.getTicker());
			preparedStatement.setFloat(2, stockDAO.getPrice());
			preparedStatement.setFloat(3, stockDAO.getChange_amount());
			preparedStatement.setFloat(4, stockDAO.getChange_percent());
			preparedStatement.setInt(5, stockDAO.getCounter());

			preparedStatement.executeUpdate();

			if (preparedStatement != null) {
				preparedStatement.close();
			}

			if (connection != null) {
				connection.close();
			}

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		}
	}

	public static void updateStock(StockDAO stockDAO) {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {

			connection = getConnection();
			preparedStatement = connection.prepareStatement(StocksContants.UPDATE_STOCK_QUERY);

			preparedStatement.setFloat(1, stockDAO.getPrice());
			preparedStatement.setFloat(2, stockDAO.getChange_amount());
			preparedStatement.setFloat(3, stockDAO.getChange_percent());
			preparedStatement.setInt(4, stockDAO.getCounter());
			preparedStatement.setString(5, stockDAO.getTicker());
			preparedStatement.executeUpdate();

			if (preparedStatement != null) {
				preparedStatement.close();
			}

			if (connection != null) {
				connection.close();
			}

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		}
	}

	public static StockDAO[] getStocks(String tickers[]) {

		StockDAO stocks[] = new StockDAO[tickers.length];
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {

			connection = getConnection();
			preparedStatement = connection.prepareStatement(StocksContants.GET_STOCKS_QUERY);
			
			for(int i = 0; i < tickers.length; i++){
				preparedStatement.setString(1, tickers[i]);
				ResultSet resultSet = preparedStatement.executeQuery();
				
				while (resultSet.next()) {
					StockDAO stockDAO = new StockDAO(resultSet.getString(StocksContants.TICKER),
							resultSet.getInt(StocksContants.COUNTER), resultSet.getFloat(StocksContants.PRICE),
							resultSet.getFloat(StocksContants.CHANGE_AMOUNT),
							resultSet.getFloat(StocksContants.CHANGE_PERCENT));
					stocks[i] = stockDAO;
				}
				
			}
						

			if (preparedStatement != null) {
				preparedStatement.close();
			}

			if (connection != null) {
				connection.close();
			}

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		}

		return stocks;
	}

	private static Connection getConnection() {

		Connection dbConnection = null;

		try {

			Class.forName(StocksContants.DB_DRIVER);

		} catch (ClassNotFoundException e) {

			System.out.println(e.getMessage());

		}

		try {

			dbConnection = DriverManager.getConnection(StocksContants.DB_CONNECTION, StocksContants.DB_USER,
					StocksContants.DB_PASSWORD);
			return dbConnection;

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		}

		return dbConnection;

	}
}
