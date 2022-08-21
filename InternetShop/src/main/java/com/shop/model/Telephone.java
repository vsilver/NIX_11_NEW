package com.shop.model;

import com.shop.model.product.Product;
import com.shop.model.product.ProductType;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Telephone extends Product {
    private String model;
    private String series;
    private String screenType;

    public Telephone(ProductType productType) {
        super(productType);
    }

    /*public void setModel(String model) {
        this.model = model;
    }*/

    @Override
    public String toString() {
        return "Telephone{" +
                "series=" + series +
                ", screenType=" + screenType +
                ", price=" + price +
                ", model=" + model +
                '}';
    }
}
