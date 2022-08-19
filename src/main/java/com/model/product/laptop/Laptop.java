package com.model.product.laptop;

import com.model.product.Product;

public class Laptop extends Product {
    /*private final String model;
    private final Manufacturer manufacturer;
    private OperationSystem operationSystem;
    private LocalDateTime creatingDate;
    private String currency;*/


    /*public Laptop(String title, int count, double price, String model, Manufacturer manufacturer) {
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
    }*/

    private Laptop() {
    }

    private LaptopType laptopType;

    public void setlaptopType(LaptopType laptopType) {
        this.laptopType = laptopType;
    }

    public LaptopType getlaptopType() {
        return laptopType;
    }

    public static class Builder {
        private Laptop newLaptop;

        public Builder(long price, LaptopType laptopType) {
            if (laptopType == null) {
                throw new IllegalArgumentException("LaptopType can not be null");
            }
            newLaptop = new Laptop();
            newLaptop.setPrice(price);
            newLaptop.setlaptopType(laptopType);
        }

        public Builder setTittle(String title){
            if (title.length() > 20) {
                throw new IllegalArgumentException("Title cant be more then 20 symbols");
            }
            newLaptop.setTitle(title);
            return this;
        }

        public Builder setCount(int count){
            if (count < 0) {
                throw new IllegalArgumentException("Count cant be less then 0");
            }
            newLaptop.setCount(count);
            return this;
        }

        public Builder setPrice(long price){
            newLaptop.setPrice(price);
            return this;
        }

        public Laptop build(){
            return newLaptop;
        }

    }

    @Override
    public String toString() {
        return "Laptop{" +
                ", id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", count=" + count +
                ", price=" + price +
                '}';
    }
}
