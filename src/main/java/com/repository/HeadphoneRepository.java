package com.repository;

import com.model.Headphone;
import com.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class HeadphoneRepository implements CrudRepository {

    private final List<Product> headphones;

    public HeadphoneRepository() {
        headphones = new LinkedList<>();
    }
    private final Logger logger = LoggerFactory.getLogger(HeadphoneRepository.class);

    @Override
    public void save(Product headphone) {
        headphones.add((Product)headphone);
    }

    @Override
    public void saveAll(List<Product> headphones) {
        for (Product headphone : headphones) {
            save(headphone);
        }
    }

    @Override
    public boolean update(Product headphone) {
        final Optional<Product> result = findById(headphone.getId());
        if (result.isEmpty()) {
            return false;
        }
        final Product originHeadphone = result.get();
        HeadphoneRepository.HeadphoneCopy.copy((Headphone)headphone, (Headphone)originHeadphone);
        return true;
    }

    @Override
    public boolean delete(String id) {
        final Iterator<Product> iterator = headphones.iterator();
        while (iterator.hasNext()) {
            final Product headphone = iterator.next();
            if (headphone.getId().equals(id)) {
                logger.info( headphone + " Was deleted ");
                iterator.remove();
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Product> getAll() {
        if (headphones.isEmpty()) {
            return Collections.emptyList();
        }
        return headphones;
    }

    @Override
    public Optional<Product> findById(String id) {
        Product result = null;
        for (Product headphone : headphones) {
            if (headphone.getId().equals(id)) {
                result = headphone;
            }
        }
        return Optional.ofNullable(result);
    }

    private static class HeadphoneCopy {
        private static void copy(final Headphone from, final Headphone to) {
            to.setCount(from.getCount());
            to.setPrice(from.getPrice());
            to.setTitle(from.getTitle());
        }
    }
}
