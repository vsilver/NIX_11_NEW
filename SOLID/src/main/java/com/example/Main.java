package com.example;

import com.example.factory.ProductFactory;
import com.example.model.NotifiableProduct;
import com.example.model.Product;
import com.example.model.ProductBundle;
import com.example.utils.ProductUtils;

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
            if (it instanceof ProductBundle) {
                productUtils.saveProductBundle((ProductBundle) it);
            } else if (it instanceof NotifiableProduct) {
                productUtils.saveNotifiableProduct((NotifiableProduct) it);
            }
        });

        productUtils.getAll().forEach(System.out::println);
        System.out.println("notifications sent: " + productUtils.filterNotifiableProductsAndSendNotifications());
    }
}
