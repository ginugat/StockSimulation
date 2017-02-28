/* Copyright 2017 Amazon.com, Inc. or its affiliates. All Rights Reserved. */
package beans;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Stock {

    public Stock() {
    }

    private int id;

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }
}
