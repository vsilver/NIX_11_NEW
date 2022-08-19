package com.example.service;

import com.example.model.NotifiableProduct;
import com.example.model.Product;
import com.example.model.ProductBundle;
import com.example.repository.IProductRepository;
import com.example.repository.ProductRepository;

import java.util.List;

public class ProductService extends AbstractProductService{

    //private ProductRepository repository = new ProductRepository();
    private static ProductService instance;

    private ProductService(IProductRepository repository) {
        super(repository);
    }

    /*public void saveNotifiableProduct(NotifiableProduct product) {
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
    }*/

    public static ProductService getInstance() {
        if (instance == null) {
            instance = new ProductService((ProductRepository.getInstance()));
        }

        return instance;
    }

}
