package com.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class Laptop extends Product {
    private final String model;
    private final Manufacturer manufacturer;


    public Laptop(String title, int count, double price, String model, Manufacturer manufacturer) {
        super(title, count, price, ProductType.SAMSUNG);
        this.model = model;
        this.manufacturer = manufacturer;
    }

    @Override
    public String toString() {
        return "Laptop{" +
                "manufacturer=" + manufacturer +
                ", id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", count=" + count +
                ", price=" + price +
                ", model=" + model +
                '}';
    }
}
