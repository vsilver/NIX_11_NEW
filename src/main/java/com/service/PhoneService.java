package com.service;

import com.model.Manufacturer;
import com.model.Phone;
import com.model.Product;
import com.repository.PhoneRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class PhoneService {
    private static final Random RANDOM = new Random();
    private static final PhoneRepository REPOSITORY = new PhoneRepository();
    private final Logger logger = LoggerFactory.getLogger(PhoneService.class);

    public void createAndSavePhones(int count) {
        List<Product> phones = new LinkedList<>();
        for (int i = 0; i < count; i++) {
            Phone phone = new Phone(
                    "Title-" + RANDOM.nextInt(1000),
                    RANDOM.nextInt(500),
                    RANDOM.nextDouble(1000.0),
                    "Model-" + RANDOM.nextInt(10),
                    getRandomManufacturer()

            );
            logger.info("{} Was Created", phone);
            phones.add(phone);
        }
        REPOSITORY.saveAll(phones);
    }

    private Manufacturer getRandomManufacturer() {
        final Manufacturer[] values = Manufacturer.values();
        final int index = RANDOM.nextInt(values.length);
        return values[index];
    }

    public void printAll() {
        for (Product phone : REPOSITORY.getAll()) {
            System.out.println(phone); // TODO: 02/07/22  
        }
    }

    public void update(Product phone) {
        REPOSITORY.update(phone);
    }

    public void delete(String id) {
        REPOSITORY.delete(id);
    }

    public List<Product> getAll() {
        return REPOSITORY.getAll();
    }

}
