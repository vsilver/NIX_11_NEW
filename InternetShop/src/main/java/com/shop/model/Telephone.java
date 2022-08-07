package com.shop.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Telephone extends Product{
    protected int model;

    public void setModel(int model) {
        this.model = model;
    }

    @Override
    public String toString() {
        return "Telephone{" +
                "series=" + series +
                ", screen_type=" + screen_type +
                ", price=" + price +
                ", model=" + model +
                '}';
    }
}
