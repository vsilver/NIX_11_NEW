package com.repository;

import com.model.Phone;
import com.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.*;

public class PhoneRepository implements CrudRepository {
    private final List<Product> phones;
    private final Logger logger = LoggerFactory.getLogger(PhoneRepository.class);

    public PhoneRepository() {
        phones = new LinkedList<>();
    }

    @Override
    public void save(Product phone) {
        phones.add(phone);
        if (phone == null) {
            final IllegalArgumentException exception = new IllegalArgumentException("Cannot save a null phone");
            logger.error(exception.getMessage(), exception);
            throw exception;
        } else {
            checkDuplicates(phone);
            phones.add(phone);
        }
    }

    private void checkDuplicates(Product phone) {
        //for (Product p : phones) {
        for (int i = 0; i < phones.size()-1 ; i++) {
           // if (phone.hashCode() == p.hashCode() && phone.equals(p)) {
            if (phone.hashCode() == phones.get(i).hashCode() && phone.equals(phones.get(i)) && phones.get(i) != phones.get(i)) {
                final IllegalArgumentException exception = new IllegalArgumentException("Duplicate phone: " +
                        phone.getId());
                logger.error(exception.getMessage(), exception);
                throw exception;
            }
        }
    }

    @Override
    public void saveAll(List<Product> phones) {
        for (Product phone : phones) {
            save(phone);
        }
    }

    @Override
    public boolean update(Product phone) {
        final Optional<Product> result = findById(phone.getId());
        if (result.isEmpty()) {
            return false;
        }
        final Product originPhone = result.get();
        PhoneCopy.copy((Phone)phone, (Phone)originPhone);
        return true;
    }

    @Override
    public boolean delete(String id) {
        final Iterator<Product> iterator = phones.iterator();
        while (iterator.hasNext()) {
            final Product phone = iterator.next();
            if (phone.getId().equals(id)) {
                logger.info( phone + " Was deleted ");
                iterator.remove();
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Product> getAll() {
        if (phones.isEmpty()) {
            return Collections.emptyList();
        }
        return phones;
    }

    @Override
    public Optional<Product> findById(String id) {
        Product result = null;
        for (Product phone : phones) {
            if (phone.getId().equals(id)) {
                result = phone;
            }
        }
        return Optional.ofNullable(result);
    }

    private static class PhoneCopy {
        private static void copy(final Phone from, final Phone to) {
            to.setCount(from.getCount());
            to.setPrice(from.getPrice());
            to.setTitle(from.getTitle());
        }
    }
}
