package com.model.product;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter

public class Laptop extends Product {
    private final String model;
    private final Manufacturer manufacturer;
    private OperationSystem operationSystem;
    private LocalDateTime creatingDate;
    private String currency;


    public Laptop(String title, int count, double price, String model, Manufacturer manufacturer) {
        super(title, count, price, ProductType.LAPTOP);
        this.model = model;
        this.manufacturer = manufacturer;
    }

    public Laptop(String title, int count, long price, String model, Manufacturer manufacturer, LocalDateTime creatingDate, String currency, OperationSystem operationSystem) {
        super(title, count, price, ProductType.LAPTOP);
        this.model = model;
        this.manufacturer = manufacturer;
        this.creatingDate = creatingDate;
        this.currency = currency;
        this.operationSystem = operationSystem;
    }

    public String getTitle() {
        return title;
    }

    public int getCount() {
        return count;
    }

    public double getPrice() {
        return price;
    }

    public static class Builder {
        private Laptop newLaptop;

        public Builder() {
            newLaptop = new Laptop();
        }

        public Builder withTitle(String title){
            newLaptop.title = title;
            return this;
        }

        public Builder withCount(int count){
            newLaptop.count = count;
            return this;
        }

        public Builder withPrice(long price){
            newLaptop.price = price;
            return this;
        }

        public Laptop build(){
            return newLaptop;
        }

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
