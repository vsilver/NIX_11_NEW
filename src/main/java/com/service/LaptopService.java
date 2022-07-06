package com.service;

import com.model.Manufacturer;
import com.model.Laptop;
import com.model.Product;
import com.repository.LaptopRepository;
import com.repository.PhoneRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class LaptopService {
    private static final Random RANDOM = new Random();
    private final LaptopRepository repository;
    private final Logger logger = LoggerFactory.getLogger(LaptopService.class);

    public LaptopService(LaptopRepository repository){
        this.repository = repository;
    }

    public void createAndSaveLaptops(int count) {
        if(count < 1){
            throw new IllegalArgumentException("count must be bigger than 0");
        }
        List<Product> laptops = new LinkedList<>();
        for (int i = 0; i < count; i++) {
            Laptop laptop = new Laptop(
                    "Title-" + RANDOM.nextInt(1000),
                    RANDOM.nextInt(500),
                    RANDOM.nextDouble(1000.0),
                    "Model-" + RANDOM.nextInt(10),
                    getRandomManufacturer()

            );
            logger.info("{} Was Created",laptop);
            laptops.add(laptop);
        }
        repository.saveAll(laptops);
    }

    private Manufacturer getRandomManufacturer() {
        final Manufacturer[] values = Manufacturer.values();
        final int index = RANDOM.nextInt(values.length);
        return values[index];
    }

    public void printAll() {
        for (Product laptop : repository.getAll()) {
            System.out.println(laptop); // TODO: 02/07/22
        }
    }

    public void update(Product laptop) {
        repository.update(laptop);
    }

    public void delete(String id) {
        repository.delete(id);
    }

    public List<Product> getAll() {
        return repository.getAll();
    }

    public void saveLaptop(Product laptop){
        if(laptop.getCount() == 0){
            laptop.setCount(-1);
        }
        repository.save(laptop);
    }
}
