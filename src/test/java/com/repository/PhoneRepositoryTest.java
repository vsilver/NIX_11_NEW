package com.repository;

import com.model.Manufacturer;
import com.model.Phone;
import com.model.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.*;

import static org.mockito.Mockito.*;

class PhoneRepositoryTest {

    private PhoneRepository target;

    private Phone phone;

    @BeforeEach
    void setUp() {
        final Random random = new Random();
        target = new PhoneRepository();
        phone = new Phone(
                "Title-" + random.nextInt(1000),
                random.nextInt(500),
                random.nextDouble(1000.0),
                "Model-" + random.nextInt(10),
                Manufacturer.APPLE
        );
    }

    @Test
    void save() {
        target.save(phone);
        final List<Product> phones = target.getAll();
        Assertions.assertEquals(1, phones.size());
        Assertions.assertEquals(phones.get(0).getId(), phone.getId());
    }

    @Test
    void save_putNull() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> target.save(null));
        final List<Product> actualResult = target.getAll();
        Assertions.assertEquals(0, actualResult.size());
    }

    @Test
    void saveAll_singlePhone() {
        target.saveAll(Collections.singletonList(phone));
        final List<Product> phones = target.getAll();
        Assertions.assertEquals(1, phones.size());
        Assertions.assertEquals(phones.get(0).getId(), phone.getId());
    }

    @Test
    void saveAll_noPhone() {
        target.saveAll(Collections.emptyList());
        final List<Product> phones = target.getAll();
        Assertions.assertEquals(0, phones.size());
    }

    @Test
    void saveAll_manyPhones() {
        final Phone otherPhone = new Phone("Title", 500, 1000.0, "Model", Manufacturer.APPLE);
        target.saveAll(List.of(phone, otherPhone));
        final List<Product> phones = target.getAll();
        Assertions.assertEquals(2, phones.size());
        Assertions.assertEquals(phones.get(0).getId(), phone.getId());
        Assertions.assertEquals(phones.get(1).getId(), otherPhone.getId());
    }

    @Test
    void saveAll_hasDuplicates() {
        final List<Product> phones = new ArrayList<>();
        phones.add(phone);
        phones.add(phone);
        Assertions.assertThrows(IllegalArgumentException.class, () ->target.saveAll(phones));
    }

    @Test
    void saveAll_hasNull() {
        final List<Product> phones = new ArrayList<>();
        phones.add(phone);
        phones.add(null);
        Assertions.assertThrows(IllegalArgumentException.class, () -> target.saveAll(phones));
        final List<Product> actualResult = target.getAll();
        Assertions.assertEquals(1, actualResult.size());
    }

    @Test
    void update() {
        final String newTitle = "New title";
        target.save(phone);
        phone.setTitle(newTitle);

        final boolean result = target.update(phone);

        Assertions.assertTrue(result);
        final List<Product> actualResult = target.getAll();
        Assertions.assertEquals(1, actualResult.size());
        Assertions.assertEquals(newTitle, actualResult.get(0).getTitle());
        Assertions.assertEquals(phone.getId(), actualResult.get(0).getId());
        Assertions.assertEquals(phone.getCount(), actualResult.get(0).getCount());
    }

    @Test
    void update_noPhone() {
        target.save(phone);
        final Phone noPhone = new Phone("Title", 500, 1000.0, "Model", Manufacturer.APPLE);
        final boolean result = target.update(noPhone);

        Assertions.assertFalse(result);
        final List<Product> actualResult = target.getAll();
        Assertions.assertEquals(1, actualResult.size());
        Assertions.assertEquals(phone.getId(), actualResult.get(0).getId());
        Assertions.assertEquals(phone.getCount(), actualResult.get(0).getCount());
    }

    @Test
    void delete() {
        target.save(phone);
        final boolean result = target.delete(phone.getId());
        Assertions.assertTrue(result);
        final List<Product> actualResult = target.getAll();
        Assertions.assertEquals(0, actualResult.size());
    }

    @Test
    void delete_noPhone() {
        target.save(phone);
        final Phone noPhone = new Phone("Title", 500, 1000.0, "Model", Manufacturer.APPLE);
        final boolean result = target.delete(noPhone.getId());
        Assertions.assertFalse(result);
        final List<Product> actualResult = target.getAll();
        Assertions.assertEquals(1, actualResult.size());
    }

    @Test
    void getAll() {
        target.save(phone);
        final List<Product> actualResult = target.getAll();
        Assertions.assertEquals(1, actualResult.size());
    }

    @Test
    void getAll_noPhones() {
        final List<Product> actualResult = target.getAll();
        Assertions.assertEquals(0, actualResult.size());
    }

    @Test
    void findById() {
        target.save(phone);
        final Optional<Product> optionalPhone = target.findById(phone.getId());
        Assertions.assertTrue(optionalPhone.isPresent());
        final Phone actualPhone = (Phone) optionalPhone.get();
        Assertions.assertEquals(phone.getId(),actualPhone.getId());
    }

    @Test
    void findById_Argument_Matcher() {
        target = mock(PhoneRepository.class);
        when(target.findById(Mockito.anyString())).thenReturn(Optional.of(phone));
        Assertions.assertEquals(phone.getId(), target.findById("1").get().getId());
    }

    @Test
    void findById_CallRealMethod() {
        target = mock(PhoneRepository.class);
        when(target.findById(Mockito.anyString())).thenCallRealMethod();
    }

    @Test
    void findById_noPhone() {
        target.save(phone);
        final Phone phone = new Phone("Title", 500, 1000.0, "Model", Manufacturer.APPLE);
        final Optional<Product> optionalPhone = target.findById(phone.getId());
        Assertions.assertFalse(optionalPhone.isPresent());


    }

    @Test
    void findById_verify() {
        target = mock(PhoneRepository.class);
        final Phone phone = new Phone("Title", 500, 1000.0, "Model", Manufacturer.APPLE);
        target.findById(phone.getId());
        Mockito.verify(target).findById(phone.getId());
    }

    @Test
    void findById_verifyTimes() {
        target = mock(PhoneRepository.class);
        final Phone phone = new Phone("Title", 500, 1000.0, "Model", Manufacturer.APPLE);
        target.findById(phone.getId());
        Mockito.verify(target, times(1)).findById(phone.getId());
    }
}