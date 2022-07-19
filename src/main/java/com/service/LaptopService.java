package com.service;

import com.model.Manufacturer;
import com.model.Laptop;
import com.model.Phone;
import com.model.Product;
import com.repository.LaptopRepository;
import com.repository.PhoneRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import com.service.Container;

public class LaptopService extends ProductService<Laptop>{
    private static final Random RANDOM = new Random();
    private final LaptopRepository repository;
    private final Logger logger = LoggerFactory.getLogger(LaptopService.class);

    public LaptopService(LaptopRepository repository){
        super(repository);
        this.repository = repository;
    }

    private Manufacturer getRandomManufacturer() {
        final Manufacturer[] values = Manufacturer.values();
        final int index = RANDOM.nextInt(values.length);
        return values[index];
    }

    @Override
    protected Laptop createProduct() {
        return new Laptop(
                Laptop.class.getSimpleName() + "-" + RANDOM.nextInt(1000),
                RANDOM.nextInt(500),
                RANDOM.nextDouble(1000.0),
                "Model-" + RANDOM.nextInt(10),
                getRandomManufacturer()
        );
    }

    public void printAll() {
        for (Product laptop : repository.getAll()) {
            System.out.println(laptop);
        }
    }

    public void update(Laptop laptop) {
        repository.update(laptop);
    }

    public void delete(String id) {
        repository.delete(id);
    }

    public List<Laptop> getAll() {
        return repository.getAll();
    }

    public void saveLaptop(Laptop laptop){
        if(laptop.getCount() == 0){
            laptop.setCount(-1);
        }
        repository.save(laptop);
    }

    /*public Optional<Laptop> findById(String id) {
        return repository.findById(id);
    }*/
}
