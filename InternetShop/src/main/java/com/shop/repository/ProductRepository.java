package com.shop.repository;

import com.shop.model.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductRepository {
    private Map<Long, Product> storage = new HashMap<>();

    public Product save(Product product) {
        return storage.put(product.getSeries(), product);
    }

    public List<Product> getAll() {
        return new ArrayList<>(storage.values());
    }
}
