package com.shop.model.television;

import com.shop.model.product.Product;
import com.shop.model.product.ProductType;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Television extends Product {
    private String diagonal;
    private String country;
    private String series;
    private String screenType;

    public Television(ProductType productType) {
        super(productType);
    }

    /*public void setDiagonal(String diagonal) {
        this.diagonal = diagonal;
    }
    public void setCountry(String country) {
        this.country = country;
    }*/

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
