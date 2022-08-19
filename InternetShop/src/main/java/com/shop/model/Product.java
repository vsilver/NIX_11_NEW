package com.shop.model;

public abstract class Product {
    protected long series;
    protected String screen_type;
    protected double price;

    public String toString() {
        return "Product{" +
                "series=" + series +
                ", screen_type=" + screen_type +
                ", price=" + price +
                '}';
    }

    /*public Long getID() {
        return id;
    }*/
}
