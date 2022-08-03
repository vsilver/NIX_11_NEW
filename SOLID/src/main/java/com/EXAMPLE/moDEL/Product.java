package com.EXAMPLE.moDEL;

import lombok.Data;

import java.util.UUID;

public abstract class Product implements IProduct{

    protected long id;
    protected boolean available;
    protected String title;
    protected double price;

    protected Product(long id, String title, double price, boolean available) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.available = available;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getID() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPrice() {
        return (long) price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public int getAmountInBundle() {
        throw new UnsupportedOperationException("Product is not a bundle");
    }

    public String generateAddressForNotification() {
        return "somerandommail@gmail.com";
    }

    public String getBasicInfo() {
        return "Product{" +
                "id=" + id +
                ", available=" + available +
                ", title='" + title + '\'' +
                ", price=" + price +
                '}';
    }
}
