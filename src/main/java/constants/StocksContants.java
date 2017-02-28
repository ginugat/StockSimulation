/**
 * 
 */
package constants;

/**
 * @author Paranjit Singh
 *
 */
public interface StocksContants {
	
	public String URL = "http://finance.google.com/finance/info?client=ig&q=NASDAQ%3AAAPL,%20AMZN,%20MSFT,%20JWN,%20JCP,%20M,%20GOOGL,%20FB,%20TSLA,%20SNE,%20SSNLF";
	public String DB_DRIVER = "com.mysql.jdbc.Driver";
	public String DB_CONNECTION = "jdbc:mysql://localhost/stocks";
	public String DB_USER = "root";
	public String DB_PASSWORD = "tejaswi";
	public String GET_STOCKS_QUERY = "SELECT * FROM Stocks WHERE ticker = ?";
	public String GET_COUNTER_QUERY = "SELECT counter FROM Stocks WHERE ticker = ?";
	public String INSERT_STOCK_QUERY = "INSERT into Stocks(ticker, price, change_amount, change_percent, counter) VALUES (?, ?, ?, ?, ?)";
	public String UPDATE_STOCK_QUERY = "UPDATE Stocks SET price=?, change_amount=?, change_percent=?, counter=? WHERE ticker=?";
	public String TICKER = "ticker";
	public String PRICE = "price";
	public String COUNTER = "counter";
	public String CHANGE_AMOUNT = "change_amount";
	public String CHANGE_PERCENT = "change_percent";

}
