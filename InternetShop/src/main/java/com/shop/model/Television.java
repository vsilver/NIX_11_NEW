package com.shop.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Television extends Product{
    protected int diagonal;
    protected Country country;

    public void setDiagonal(int diagonal) {
        this.diagonal = diagonal;
    }
    public void setCountry(Country country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "Television{" +
                "series=" + series +
                ", screenType=" + screenType +
                ", price=" + price +
                ", diagonal=" + diagonal +
                ", country=" + country +
                '}';
    }
}
