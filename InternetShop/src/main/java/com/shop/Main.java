package com.shop;

import com.shop.factory.ProductFactory;
import com.shop.model.Product;
import com.shop.model.Telephone;
import com.shop.model.Television;
import com.shop.utils.ProductUtils;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ProductFactory productFactory = ProductFactory.getInstance();
        ProductUtils productUtils = ProductUtils.getInstance();

        List<Product> products = new ArrayList<>();
        products.add(productFactory.generateRandomProduct());
        products.add(productFactory.generateRandomProduct());
        products.add(productFactory.generateRandomProduct());
        products.add(productFactory.generateRandomProduct());
        products.add(productFactory.generateRandomProduct());
        products.add(productFactory.generateRandomProduct());
        products.add(productFactory.generateRandomProduct());
        products.forEach(it -> {
            if (it instanceof Telephone) {
                productUtils.saveTelephone((Telephone) it);
            } else if (it instanceof Television) {
                productUtils.saveTelevision((Television) it);
            }
        });

        //System.out.println("notifications sent: " + products);
        productUtils.getAll().forEach(System.out::println);
    }
}
