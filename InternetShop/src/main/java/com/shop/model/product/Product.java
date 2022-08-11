package com.shop.model.product;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public abstract class Product {
    private String id;
    protected long price;
    private ProductType productType;

    protected Product(ProductType productType) {
        id = UUID.randomUUID().toString();
        this.productType = productType;
    }

    public String toString() {
        return "Product{" +
                "id=" + id +
                ", price=" + price +
                ", productType=" + productType +
                '}';
    }
}
