package com.service;

import com.model.product.Product;

import java.util.Random;

public class Container <T extends Product>{
    private T product;
    private static final Random RANDOM = new Random();

    public T getProduct() {
        return product;
    }

    public void setProduct(T product) {
        this.product = product;
    }

    public void showProduct(T product) {
        /*for (Phone phone : repository.getAll()) {
            System.out.println(phone);
        }*/
        System.out.println(product);
    }

    public void getDiscount(T product) {
        int lowerBoundPercent = 10;
        int upperBoundPercent = 30;
        int discount = RANDOM.nextInt(lowerBoundPercent, upperBoundPercent);
        long actualPrice = (long) product.getPrice();
        long discountPrice = actualPrice - (actualPrice * discount / 100);
        product.setPrice(discountPrice);
    }

    public <X extends Number> void increaseCount(T product, X count) {
        product.setCount(product.getCount() + count.intValue());
    }
}
