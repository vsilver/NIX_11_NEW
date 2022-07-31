package com.service;

import com.model.product.Product;
import com.repository.CrudRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public abstract class ProductService<T extends Product>  {

    protected static final Random RANDOM = new Random();
    protected final CrudRepository<T> repository;
    private final Logger logger = LoggerFactory.getLogger(ProductService.class);

    protected ProductService(CrudRepository<T> repository){
        this.repository =  repository;
    }

    public void createAndSave(int count) {
        List<T> products = new LinkedList<>();
        if(count < 1){
            throw new IllegalArgumentException("count must be bigger than 0");
        }
        for (int i = 0; i < count; i++) {
            final T phone = createProduct();
            logger.info("Product {} Was Created", phone);
            products.add(phone);
        }
        repository.saveAll(products);
    }

    public abstract T createProduct();

    public void printAll() {
        for (T phone : repository.getAll()) {
            System.out.println(phone);
        }
    }

    public T findById(String id) {
        return repository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    public void update(T product) {
        repository.update(product);
    }

    public void delete(String id) {
        repository.delete(id);
    }

    public List<T> findAll() {
        return repository.getAll();
    }

    public void save(T product) {
        if(product.getCount() == 0){
            product.setCount(-1);
        }
        repository.save(product);
    }

}
