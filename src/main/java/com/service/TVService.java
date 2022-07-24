package com.service;

import com.model.product.TV;
import com.repository.CrudRepository;
import com.repository.PhoneRepository;
import com.repository.TVRepository;

public class TVService extends ProductService<TV>{
    private static TVService instance;

    public TVService(CrudRepository<TV> repository) {
        super(repository);
    }

    @Override
    public TV createProduct() {
        return new TV(
                TV.class.getSimpleName() + "-" + RANDOM.nextInt(1000),
                RANDOM.nextInt(500),
                RANDOM.nextDouble(1000.0)
        );
    }
    public static TVService getInstance() {
        if (instance == null) {
            instance = new TVService(TVRepository.getInstance());
        }
        return instance;
    }
}
