package com.model.product;

import java.util.Comparator;

public class TV extends Product {
    private final String model;
    private final Manufacturer manufacturer;

    public TV(String title, int count, double price, String model, Manufacturer manufacturer) {
        super(title, count, price);
        this.model = model;
        this.manufacturer = manufacturer;
    }

    public static class TVComparator implements Comparator<TV> {
        @Override
        public int compare(TV o1, TV o2) {
            return o1.getId().compareTo(o2.getId());
        }
    }

    @Override
    public String toString() {
        return "TV{" +
                "manufacturer=" + manufacturer +
                ", id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", count=" + count +
                ", price=" + price +
                ", model=" + model +
                '}';
    }
}
