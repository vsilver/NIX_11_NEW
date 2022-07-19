package com.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Headphone extends Product {
    private final String model;
    private final Manufacturer manufacturer;
    public Headphone(String title, int count, double price, String model, Manufacturer manufacturer) {
        super(title, count, price, ProductType.JBL);
        this.model = model;
        this.manufacturer = manufacturer;
    }

    @Override
    public String toString() {
        return "Headphones{" +
                "manufacturer=" + manufacturer +
                ", id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", count=" + count +
                ", price=" + price +
                ", model=" + model +
                '}';
    }
}
