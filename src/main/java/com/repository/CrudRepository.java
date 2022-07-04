package com.repository;

import com.model.Phone;
import com.model.Product;

import java.util.List;
import java.util.Optional;

public interface CrudRepository {
    void save(Product phone);

    void saveAll(List<Product> product);

    boolean update(Product product);

    boolean delete(String id);

    List<Product> getAll();

    Optional<Product> findById(String id);
}
