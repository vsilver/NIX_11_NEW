package com.shop.model;

import lombok.Data;

@Data

public abstract class Product {
    protected long series;
    protected ScreenType screenType;
    protected double price;

    public void setScreenType(ScreenType screenType) {
        this.screenType = screenType;
    }

    public ScreenType getscreenType() {
        return screenType;
    }

    public String toString() {
        return "Product{" +
                "series=" + series +
                ", screenType=" + screenType +
                ", price=" + price +
                '}';
    }
}
