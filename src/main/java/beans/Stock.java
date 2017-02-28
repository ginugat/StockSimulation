/* Copyright 2017 Amazon.com, Inc. or its affiliates. All Rights Reserved. */
package beans;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Stock {

    private String id;
    private int counter;
    private long quantity;
    private float price;

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Stock(String id, int counter, float price, long quantity){
        this.id = id;
        this.counter = counter;
        this.price = price;
        this.quantity = quantity;
    }
    /*public Stock(String id, int counter, long quantity){
	    this.id = id;
	    this.counter = counter;
	    this.quantity = quantity;
    }*/

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }




}
