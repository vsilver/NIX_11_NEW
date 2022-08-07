package com.shop.utils;

import com.shop.model.Product;
import com.shop.model.Telephone;
import com.shop.model.Television;
import com.shop.repository.ProductRepository;

import java.util.List;

public class ProductUtils {
    private ProductRepository repository = new ProductRepository();
    private static ProductUtils instance;

    public void saveTelephone(Telephone product) {
        repository.save(product);
    }

    public void saveTelevision(Television product) {
        repository.save(product);
    }

    /*public int filterNotifiableProductsAndSendNotifications() {
        int notifications = 0;

        List<NotifiableProduct> products = repository.getAll()
                .stream()
                .filter(NotifiableProduct.class::isInstance)
                .map(NotifiableProduct.class::cast)
                .toList();

        return products.size();
    }*/

    public List<Product> getAll() {
        return repository.getAll();
    }

    public static ProductUtils getInstance() {
        if (instance == null) {
            instance = new ProductUtils();
        }

        return instance;
    }
}
