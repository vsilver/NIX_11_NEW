package com.service;

import com.model.TV;
import com.repository.CrudRepository;

public class TVService extends ProductService<TV>{

    public TVService(CrudRepository<TV> repository) {
        super(repository);
    }

    @Override
    protected TV createProduct() {
        return new TV(
                TV.class.getSimpleName() + "-" + RANDOM.nextInt(1000),
                RANDOM.nextInt(500),
                RANDOM.nextDouble(1000.0)
        );
    }
}
