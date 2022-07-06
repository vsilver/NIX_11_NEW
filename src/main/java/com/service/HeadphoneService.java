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
    private static final HeadphoneRepository REPOSITORY = new HeadphoneRepository();
    private final Logger logger = LoggerFactory.getLogger(HeadphoneService.class);

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
            logger.info(headphone + "Was Created");
            headphones.add(headphone);
        }
        REPOSITORY.saveAll(headphones);
    }

    private Manufacturer getRandomManufacturer() {
        final Manufacturer[] values = Manufacturer.values();
        final int index = RANDOM.nextInt(values.length);
        return values[index];
    }

    public void printAll() {
        for (Product headphone : REPOSITORY.getAll()) {
            System.out.println(headphone); // TODO: 02/07/22
        }
    }

    public void update(Product headphone) {
        REPOSITORY.update(headphone);
    }

    public void delete(String id) {
        REPOSITORY.delete(id);
    }

    public List<Product> getAll() {
        return REPOSITORY.getAll();
    }
}
