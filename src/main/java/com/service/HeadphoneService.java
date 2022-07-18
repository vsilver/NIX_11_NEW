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

public class HeadphoneService extends ProductService<Product>{
    private static final Random RANDOM = new Random();
    private final HeadphoneRepository repository;
    private final Logger logger = LoggerFactory.getLogger(HeadphoneService.class);

    public HeadphoneService(HeadphoneRepository repository){
        super(repository);
        this.repository = repository;
    }

    @Override
    protected Product createProduct() {
        return null;
    }

    public void createAndSaveHeadphones(int count) {
        List<Headphone> headphones = new LinkedList<>();
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
            System.out.println(headphone);
        }
    }

    public void update(Headphone headphone) {
        repository.update(headphone);
    }

    public void delete(String id) {
        repository.delete(id);
    }

    public List<Headphone> getAll() {
        return repository.getAll();
    }
}
