package com.service;

import com.model.Product;
import com.repository.PhoneRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.model.Manufacturer;
import com.model.Phone;
import org.junit.jupiter.api.Assertions;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class PhoneServiceTest {

    private Phone phone;

    private PhoneService target;

    private PhoneRepository repository;

    @BeforeEach
    void setUp() {
        final Random random = new Random();
        repository = mock(PhoneRepository.class);
        target = new PhoneService(repository);
    }

    @Test
    void createAndSavePhones_negativeCount() {
        assertThrows(IllegalArgumentException.class, () -> target.createAndSavePhones(-1));
    }

    @Test
    void createAndSavePhone_zeroCount() {
        assertThrows(IllegalArgumentException.class, () -> target.createAndSavePhones(0));
    }

    @Test
    void createAndSavePhones() {
        target.createAndSavePhones(2);
        Mockito.verify(repository).saveAll(Mockito.anyList());
    }

    @Test
    void getAll() {
        target.getAll();
        Mockito.verify(repository).getAll();
    }

    @Test
    void getAll_Argument_Matcher() {
        target.getAll();
        PhoneService target = mock(PhoneService.class);
        Phone phone = new Phone("Title", 500, 1000.0, "Model", Manufacturer.APPLE);
        when(target.getAll()).thenReturn(Arrays.asList(phone));
        Assertions.assertEquals(phone.getId(), target.getAll().stream().findFirst().get().getId());

    }

    @Test
    void printAll() {
        target.printAll();
        Mockito.verify(repository).getAll();
    }

    @Test
    void getAll_noPhones() {
        final List<Product> actualResult = target.getAll();
        Assertions.assertEquals(0, actualResult.size());
    }

    @Test
    void findById_CallRealMethod() {
        when(target.findById(Mockito.anyString())).thenCallRealMethod();
    }

    @Test
    void savePhone_zeroCount() {
        final Phone phone = new Phone("Title", 0, 1000.0, "Model", Manufacturer.APPLE);
        target.savePhone(phone);

        ArgumentCaptor<Phone> argument = ArgumentCaptor.forClass(Phone.class);
        Mockito.verify(repository).save(argument.capture());
        Assertions.assertEquals("Title", argument.getValue().getTitle());
        Assertions.assertEquals(-1, argument.getValue().getCount());
    }

    @Test
    void savePhone_verifyTimes() {
        final Phone phone = new Phone("Title", 100, 1000.0, "Model", Manufacturer.APPLE);
        target.savePhone(phone);

        ArgumentCaptor<Phone> argument = ArgumentCaptor.forClass(Phone.class);
        Mockito.verify(repository, Mockito.times(1)).save(argument.capture());
        Assertions.assertEquals("Title", argument.getValue().getTitle());
    }

    @Test
    void savePhone() {
        final Phone phone = new Phone("Title", 100, 1000.0, "Model", Manufacturer.APPLE);
        target.savePhone(phone);

        ArgumentCaptor<Phone> argument = ArgumentCaptor.forClass(Phone.class);
        Mockito.verify(repository).save(argument.capture());
        Assertions.assertEquals("Title", argument.getValue().getTitle());
    }

    /*@Test
    public void printIfPresent_test(String id) {
        //target.save(phone);
        //target.printIfPresent(phone.getId());
        final Optional<Product> optionalPhone;
        target.printIfPresent(phone.getId());
        //Assertions.assertTrue(target.printIfPresent(phone.getId()));
        *//*final Phone actualPhone = (Phone) optionalPhone.get();
        Assertions.assertEquals(phone.getId(),actualPhone.getId());
        return optionalPhone;*//*
    }*/

    /*@Test
    public void printIfPresent_test_negative(String id) {
        //target.save(phone);
        target.printIfPresent(phone.getId());
        //final Optional<Product> optionalPhone = target.printIfPresent(phone.getId());
        //Assertions.assertTrue(optionalPhone.ifPresent(phone.getId()));
        *//*final Phone actualPhone = (Phone) optionalPhone.get();
        Assertions.assertEquals(phone.getId(),actualPhone.getId());
        return optionalPhone;*//*
    }*/

}