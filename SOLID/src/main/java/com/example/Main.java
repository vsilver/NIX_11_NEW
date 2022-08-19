package com.example;

import com.example.factory.ProductFactory;
import com.example.model.Product;
import com.example.service.NotificationService;
import com.example.service.ProductService;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        ProductFactory productFactory = ProductFactory.getInstance();
        ProductService productService = ProductService.getInstance();
        NotificationService notificationService = NotificationService.getInstance();

        List<Product> products = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            products.add(productFactory.generateRandomProduct());
        }

        products.forEach(productService::save);
        System.out.println("Products: " + productService.getAll());
        System.out.println("notifications sent: " + notificationService.filterNotifiableProductsAndSendNotifications());
    }
}
