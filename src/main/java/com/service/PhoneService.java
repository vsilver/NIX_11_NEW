package com.service;

import com.model.Manufacturer;
import com.model.Phone;
import com.model.Product;
import com.repository.PhoneRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class PhoneService {
    private static final Random RANDOM = new Random();
    private final PhoneRepository repository;
    private final Logger logger = LoggerFactory.getLogger(PhoneService.class);

    public PhoneService(PhoneRepository repository){
        this.repository = repository;
    }

    public void createAndSavePhones(int count) {
        List<Product> phones = new LinkedList<>();
        if(count < 1){
            throw new IllegalArgumentException("count must be bigger than 0");
        }
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
        repository.saveAll(phones);
    }

    private Manufacturer getRandomManufacturer() {
        final Manufacturer[] values = Manufacturer.values();
        final int index = RANDOM.nextInt(values.length);
        return values[index];
    }

    public void printAll() {
        for (Product phone : repository.getAll()) {
            System.out.println(phone); // TODO: 02/07/22  
        }
    }

    public void update(Product phone) {
        repository.update(phone);
    }

    public void delete(String id) {
        repository.delete(id);
    }

    public List<Product> getAll() {
        return repository.getAll();
    }

    public void savePhone(Product phone){
        if(phone.getCount() == 0){
            phone.setCount(-1);
        }
        repository.save(phone);
    }

    public Optional<Product> findById(String id) {
        return repository.findById(id);
    }
}
