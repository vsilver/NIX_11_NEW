package com.service;

import com.annotation.Autowired;
import com.annotation.Singleton;
import com.model.product.Manufacturer;
import com.model.product.TV;
import com.repository.CrudRepository;
import com.repository.PhoneRepository;
import com.repository.TVRepository;

@Singleton
public class TVService extends ProductService<TV>{
    private static TVService instance;

    @Autowired
    public TVService(CrudRepository<TV> repository) {
        super(repository);
    }

    @Override
    public TV createProduct() {
        return new TV(
                TV.class.getSimpleName() + "-" + RANDOM.nextInt(1000),
                RANDOM.nextInt(500),
                RANDOM.nextDouble(1000.0),
                "Model-" + RANDOM.nextInt(10),
                getRandomManufacturer()
        );
    }

    private Manufacturer getRandomManufacturer() {
        final Manufacturer[] values = Manufacturer.values();
        final int index = RANDOM.nextInt(values.length);
        return values[index];
    }

    public static TVService getInstance() {
        if (instance == null) {
            instance = new TVService(TVRepository.getInstance());
        }
        return instance;
    }
}
