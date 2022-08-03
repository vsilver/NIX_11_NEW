package com.EXAMPLE.utils;

import com.EXAMPLE.model.NotifiableProduct;
import com.EXAMPLE.model.Product;
import com.EXAMPLE.model.ProductBundle;
import com.EXAMPLE.repository.ProductRepository;

import java.util.List;

public class ProductUtils {

    private ProductRepository repository = new ProductRepository();
    private static ProductUtils instance;

    public void saveNotifiableProduct(NotifiableProduct product) {
        repository.save(product);
    }

    public void saveProductBundle(ProductBundle product) {
        repository.save(product);
    }

    public int filterNotifiableProductsAndSendNotifications() {
        int notifications = 0;

        List<NotifiableProduct> products = repository.getAll()
                .stream()
                .filter(NotifiableProduct.class::isInstance)
                .map(NotifiableProduct.class::cast)
                .toList();

        return products.size();
    }

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
