package com.service;

import com.model.Headphone;
import com.model.Manufacturer;
import com.model.Product;
import com.repository.HeadphoneRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class HeadphoneService {
    private static final Random RANDOM = new Random();
    private final HeadphoneRepository repository;
    private final Logger logger = LoggerFactory.getLogger(HeadphoneService.class);

    public HeadphoneService(HeadphoneRepository repository){
        this.repository = repository;
    }

    public void createAndSaveHeadphones(int count) {
        List<Product> headphones = new LinkedList<>();
        for (int i = 0; i < count; i++) {
            Headphone headphone = new Headphone(
                    "Title-" + RANDOM.nextInt(1000),
                    RANDOM.nextInt(500),
                    RANDOM.nextDouble(1000.0),
                    "Model-" + RANDOM.nextInt(10),
                    getRandomManufacturer()

            );
            logger.info("{} Was Created", headphone);
            headphones.add(headphone);
        }
        repository.saveAll(headphones);
    }

    private Manufacturer getRandomManufacturer() {
        final Manufacturer[] values = Manufacturer.values();
        final int index = RANDOM.nextInt(values.length);
        return values[index];
    }

    public void printAll() {
        for (Product headphone : repository.getAll()) {
            System.out.println(headphone); // TODO: 02/07/22
        }
    }

    public void update(Product headphone) {
        repository.update(headphone);
    }

    public void delete(String id) {
        repository.delete(id);
    }

    public List<Product> getAll() {
        return repository.getAll();
    }
}
