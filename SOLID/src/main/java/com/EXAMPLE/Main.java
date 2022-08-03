package com.EXAMPLE;

import com.EXAMPLE.factory.ProductFactory;
import com.EXAMPLE.model.NotifiableProduct;
import com.EXAMPLE.model.Product;
import com.EXAMPLE.model.ProductBundle;
import com.EXAMPLE.utils.ProductUtils;

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
