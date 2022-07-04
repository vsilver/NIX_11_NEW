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
    private static final LaptopRepository REPOSITORY = new LaptopRepository();
    private final Logger logger = LoggerFactory.getLogger(LaptopService.class);

    public void createAndSaveLaptops(int count) {
        List<Product> laptops = new LinkedList<>();
        for (int i = 0; i < count; i++) {
            Laptop laptop = new Laptop(
                    "Title-" + RANDOM.nextInt(1000),
                    RANDOM.nextInt(500),
                    RANDOM.nextDouble(1000.0),
                    "Model-" + RANDOM.nextInt(10),
                    getRandomManufacturer()

            );
            logger.info(laptop + "Was Created");
            laptops.add(laptop);
        }
        REPOSITORY.saveAll(laptops);
    }

    private Manufacturer getRandomManufacturer() {
        final Manufacturer[] values = Manufacturer.values();
        final int index = RANDOM.nextInt(values.length);
        return values[index];
    }

    public void printAll() {
        for (Product laptop : REPOSITORY.getAll()) {
            System.out.println(laptop); // TODO: 02/07/22
        }
    }

    public void update(Product laptop) {
        REPOSITORY.update(laptop);
    }

    public void delete(String id) {
        REPOSITORY.delete(id);
    }

    public List<Product> getAll() {
        return REPOSITORY.getAll();
    }
}
