package com.shop.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Television extends Product{
    protected int diagonal;
    protected int country;

    public void setDiagonal(int diagonal) {
        this.diagonal = diagonal;
    }
    public void setCountry(int country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "Telephone{" +
                "series=" + series +
                ", screen_type=" + screen_type +
                ", price=" + price +
                ", diagonal=" + diagonal +
                ", country=" + country +
                '}';
    }
}
