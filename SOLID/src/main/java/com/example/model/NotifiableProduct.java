package com.example.model;

import lombok.Setter;

@Setter
public class NotifiableProduct extends Product {

    protected String channel;

    @Override
    public String toString() {
        return "NotifiableProduct{" +
                "channel='" + channel + '\'' +
                ", id=" + id +
                ", available=" + available +
                ", title='" + title + '\'' +
                ", price=" + price +
                '}';
    }
}
