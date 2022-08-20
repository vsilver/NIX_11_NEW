package com.repository;

import com.annotation.Autowired;
import com.annotation.Singleton;
import com.model.product.Phone;
import com.model.product.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.*;

@Singleton
public class PhoneRepository implements CrudRepository<Phone> {
    private final List<Phone> phones;
    private final Logger logger = LoggerFactory.getLogger(PhoneRepository.class);

    private static PhoneRepository instance;

    @Autowired
    public PhoneRepository() {
        phones = new LinkedList<>();
    }

    public static PhoneRepository getInstance() {
        if (instance == null) {
            instance = new PhoneRepository();
        }
        return instance;
    }

    @Override
    public void save(Phone phone) {
        if (phone == null) {
            final IllegalArgumentException exception = new IllegalArgumentException("Cannot save a null phone");
            logger.error(exception.getMessage(), exception);
            throw exception;
        } else {
            checkDuplicates(phone);
            phones.add(phone);
        }
    }

    @Override
    public void saveAll(List<Phone> products) {
        for (Phone phone : products) {
            save(phone);
        }
    }

    private void checkDuplicates(Phone phone) {
        for (Phone p : phones) {
            if (phone.hashCode() == p.hashCode() && phone.equals(p)) {
                final IllegalArgumentException exception = new IllegalArgumentException("Duplicate phone: " +
                        phone.getId());
                logger.error(exception.getMessage(), exception);
                throw exception;
            }
        }
    }

    @Override
    public boolean update(Phone phone) {
        final Optional<Phone> result = findById(phone.getId());
        if (result.isEmpty()) {
            return false;
        }
        final Product originPhone = result.get();
        PhoneCopy.copy((Phone)phone, (Phone)originPhone);
        return true;
    }

    @Override
    public boolean delete(String id) {
        final Iterator<Phone> iterator = phones.iterator();
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
    public List<Phone> getAll() {
        if (phones.isEmpty()) {
            return Collections.emptyList();
        }
        return phones;
    }

    @Override
    public Optional<Phone> findById(String id) {
        Phone result = null;
        for (Product phone : phones) {
            if (phone.getId().equals(id)) {
                result = (Phone) phone;
            }
        }
        return Optional.ofNullable(result);
    }

    public List<Phone> findByModel(final String model) {
        List<Phone> phoneList = new ArrayList<>(phones.size());
        for (Phone phone : phones) {
            if (phone.getModel().equals(model)) {
                phoneList.add(phone);
            }
        }
        return phoneList;
    }

    private static class PhoneCopy {
        private static void copy(final Phone from, final Phone to) {
            to.setCount(from.getCount());
            to.setPrice(from.getPrice());
            to.setTitle(from.getTitle());
        }
    }
}
