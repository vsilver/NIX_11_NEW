package com.repository;

import com.model.Laptop;
import com.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class LaptopRepository implements CrudRepository {

    private final List<Product> laptops;
    private final Logger logger = LoggerFactory.getLogger(LaptopRepository.class);

    public LaptopRepository() {
        laptops = new LinkedList<>();
    }

    @Override
    public void save(Product laptop) {
        laptops.add(laptop);

    }

    @Override
    public void saveAll(List<Product> laptops) {
        for (Product laptop : laptops) {
            save(laptop);
        }
    }

    @Override
    public boolean update(Product laptop) {
        final Optional<Product> result = findById(laptop.getId());
        if (result.isEmpty()) {
            return false;
        }
        final Product originLaptop = result.get();
        LaptopRepository.LaptopCopy.copy((Laptop)laptop, (Laptop)originLaptop);
        return true;
    }

    @Override
    public boolean delete(String id) {
        final Iterator<Product> iterator = laptops.iterator();
        while (iterator.hasNext()) {
            final Product laptop = iterator.next();
            if (laptop.getId().equals(id)) {
                logger.info( laptop + " Was deleted ");
                iterator.remove();
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Product> getAll() {
        if (laptops.isEmpty()) {
            return Collections.emptyList();
        }
        return laptops;
    }

    @Override
    public Optional<Product> findById(String id) {
        Product result = null;
        for (Product laptop : laptops) {
            if (laptop.getId().equals(id)) {
                result = laptop;
            }
        }
        return Optional.ofNullable(result);
    }

    private static class LaptopCopy {
        private static void copy(final Laptop from, final Laptop to) {
            to.setCount(from.getCount());
            to.setPrice(from.getPrice());
            to.setTitle(from.getTitle());
        }
    }
}
