package com.repository;

import com.model.product.Headphone;
import com.model.product.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class HeadphoneRepository implements CrudRepository<Headphone> {

    private final List<Headphone> headphones;
    private final Logger logger = LoggerFactory.getLogger(HeadphoneRepository.class);

    public HeadphoneRepository() {
        headphones = new LinkedList<>();
    }

    @Override
    public void save(Headphone headphone) {
        if (headphone == null) {
            final IllegalArgumentException exception = new IllegalArgumentException("Cannot save a null phone");
            logger.error(exception.getMessage(), exception);
            throw exception;
        } else {
            checkDuplicates(headphone);
            headphones.add(headphone);
        }
    }

    @Override
    public void saveAll(List<Headphone> headphones) {
        for (Headphone headphone : headphones) {
            save(headphone);
        }
    }

    private void checkDuplicates(Headphone headphone) {
        for (Headphone p : headphones) {
            if (headphone.hashCode() == p.hashCode() && headphone.equals(p)) {
                final IllegalArgumentException exception = new IllegalArgumentException("Duplicate phone: " +
                        headphone.getId());
                logger.error(exception.getMessage(), exception);
                throw exception;
            }
        }
    }

    @Override
    public boolean update(Headphone headphone) {
        final Optional<Headphone> result = findById(headphone.getId());
        if (result.isEmpty()) {
            return false;
        }
        final Product originHeadphone = result.get();
        HeadphoneRepository.HeadphoneCopy.copy((Headphone)headphone, (Headphone)originHeadphone);
        return true;
    }

    @Override
    public boolean delete(String id) {
        final Iterator<Headphone> iterator = headphones.iterator();
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
    public List<Headphone> getAll() {
        if (headphones.isEmpty()) {
            return Collections.emptyList();
        }
        return headphones;
    }

    @Override
    public Optional<Headphone> findById(String id) {
        Headphone result = null;
        for (Headphone headphone : headphones) {
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
