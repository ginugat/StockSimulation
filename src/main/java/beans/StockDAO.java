/**
 * 
 */
package beans;

/**
 * @author Paranjit Singh
 *
 */
public class StockDAO {

	private String ticker;
	private int counter;
	private float price;
	private float change_amount;
	private float change_percent;


	/**
	 * @param ticker
	 * @param counter
	 * @param price
	 * @param change_amount
	 * @param change_percent
	 */
	public StockDAO(String ticker, int counter, float price, float change_amount, float change_percent) {
		this.ticker = ticker;
		this.counter = counter;
		this.price = price;
		this.change_amount = change_amount;
		this.change_percent = change_percent;
	}

	/**
	 * @return the ticker
	 */
	public String getTicker() {
		return ticker;
	}

	/**
	 * @param ticker
	 *            the ticker to set
	 */
	public void setTicker(String ticker) {
		this.ticker = ticker;
	}

	/**
	 * @return the counter
	 */
	public int getCounter() {
		return counter;
	}

	/**
	 * @param counter
	 *            the counter to set
	 */
	public void setCounter(int counter) {
		this.counter = counter;
	}

	/**
	 * @return the price
	 */
	public float getPrice() {
		return price;
	}

	/**
	 * @param price
	 *            the price to set
	 */
	public void setPrice(float price) {
		this.price = price;
	}

	/**
	 * @return the change_amount
	 */
	public float getChange_amount() {
		return change_amount;
	}

	/**
	 * @param change_amount
	 *            the change_amount to set
	 */
	public void setChange_amount(float change_amount) {
		this.change_amount = change_amount;
	}

	/**
	 * @return the change_percent
	 */
	public float getChange_percent() {
		return change_percent;
	}

	/**
	 * @param change_percent
	 *            the change_percent to set
	 */
	public void setChange_percent(float change_percent) {
		this.change_percent = change_percent;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "StockDAO [ticker=" + ticker + ", counter=" + counter + ", price=" + price + ", change_amount="
				+ change_amount + ", change_percent=" + change_percent + "]";
	}

}
