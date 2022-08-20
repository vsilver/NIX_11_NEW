package com.repository;

import com.annotation.Autowired;
import com.annotation.Singleton;
import com.model.product.laptop.Laptop;
import com.model.product.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

@Singleton
public class LaptopRepository implements CrudRepository<Laptop> {

    private final List<Laptop> laptops;
    private final Logger logger = LoggerFactory.getLogger(LaptopRepository.class);
    private static LaptopRepository instance;

    @Autowired
    public LaptopRepository() {
        laptops = new LinkedList<>();
    }
    public static LaptopRepository getInstance() {
        if (instance == null) {
            instance = new LaptopRepository();
        }
        return instance;
    }

    @Override
    public void save(Laptop laptop) {
        if (laptop == null) {
            final IllegalArgumentException exception = new IllegalArgumentException("Cannot save a null phone");
            logger.error(exception.getMessage(), exception);
            throw exception;
        } else {
            checkDuplicates(laptop);
            laptops.add(laptop);
        }

    }

    private void checkDuplicates(Laptop laptop) {
        for (Laptop p : laptops) {
            if (laptop.hashCode() == p.hashCode() && laptop.equals(p)) {
                final IllegalArgumentException exception = new IllegalArgumentException("Duplicate phone: " +
                        laptop.getId());
                logger.error(exception.getMessage(), exception);
                throw exception;
            }
        }
    }

    @Override
    public void saveAll(List<Laptop> laptops) {
        for (Laptop laptop : laptops) {
            save(laptop);
        }
    }

    @Override
    public boolean update(Laptop laptop) {
        final Optional<Laptop> result = findById(laptop.getId());
        if (result.isEmpty()) {
            return false;
        }
        final Product originLaptop = result.get();
        LaptopRepository.LaptopCopy.copy((Laptop)laptop, (Laptop)originLaptop);
        return true;
    }

    @Override
    public boolean delete(String id) {
        final Iterator<Laptop> iterator = laptops.iterator();
        while (iterator.hasNext()) {
            final Laptop laptop = iterator.next();
            if (laptop.getId().equals(id)) {
                logger.info( laptop + " Was deleted ");
                iterator.remove();
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Laptop> getAll() {
        if (laptops.isEmpty()) {
            return Collections.emptyList();
        }
        return laptops;
    }

    @Override
    public Optional<Laptop> findById(String id) {
        Laptop result = null;
        for (Laptop laptop : laptops) {
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
