package com.service;

import com.model.product.Manufacturer;
import com.model.product.Phone;
import com.model.product.Product;
import com.repository.PhoneRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;


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
        assertThrows(IllegalArgumentException.class, () -> target.createAndSave(-1));
    }

    @Test
    void createAndSavePhone_zeroCount() {
        assertThrows(IllegalArgumentException.class, () -> target.createAndSave(0));
    }

    @Test
    void createAndSavePhones() {
        target.createAndSave(2);
        verify(repository).saveAll(Mockito.anyList());
    }

    @Test
    void getAll() {
        target.getAll();
        verify(repository).getAll();
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
        verify(repository).getAll();
    }

    @Test
    void getAll_noPhones() {
        final List<Phone> actualResult = target.getAll();
        Assertions.assertEquals(0, actualResult.size());
    }

    @Test
    void updateIfPresent_noPhones() {
        final Phone phone = target.createAndSavePhone();
        target.updateIfPresent(phone);

        verify(repository).findById(phone.getId());
        verify(repository, times(0)).update(phone);
    }

    @Test
    void findByIdOrCreateDefault_present() {
        final Phone phone = target.createAndSavePhone();
        when(repository.findById(phone.getId())).thenReturn(Optional.of(phone));
        Product foundedPhone = target.findByIdOrCreateDefault(phone.getId());

        verify(repository).findById(phone.getId());
        Assertions.assertEquals("Title", foundedPhone.getTitle());
    }

    @Test
    void findByIdOrCreateDefault_noPhones() {
        final Phone phone = target.createAndSavePhone();
        Product foundedBall = target.findByIdOrCreateDefault(phone.getId());

        verify(repository).findById(phone.getId());
        Assertions.assertEquals("", foundedBall.getTitle());
        Assertions.assertEquals(0, foundedBall.getCount());
        Assertions.assertEquals(0, foundedBall.getPrice());
    }

    @Test
    void findByIdOrCreateRandom_present() {
        final Phone phone = target.createAndSavePhone();
        when(repository.findById(phone.getId())).thenReturn(Optional.of(phone));
        Product foundedPhone = target.findByIdOrCreateRandom(phone.getId());

        verify(repository).findById(phone.getId());
        Assertions.assertEquals("Title", foundedPhone.getTitle());
    }

    @Test
    void findByIdOrCreateRandom_noPhones() {
        final Phone phone = target.createAndSavePhone();
        Product foundedBall = target.findByIdOrCreateRandom(phone.getId());

        verify(repository).findById(phone.getId());
        Assertions.assertNotEquals(phone.getId(), foundedBall.getId());
    }

    @Test
    void mapPhoneToString_present() {
        final Phone phone = target.createAndSavePhone();
        when(repository.findById(phone.getId())).thenReturn(Optional.of(phone));

        String actual = target.mapPhoneToString(phone.getId());
        verify(repository).findById(phone.getId());
        Assertions.assertEquals(phone.toString(), actual);
    }

    @Test
    void mapPhoneToString_noPhones() {
        final Phone phone = target.createAndSavePhone();
        String actual = target.mapPhoneToString(phone.getId());
        verify(repository).findById(phone.getId());
        Assertions.assertEquals("Not Found", actual);
    }

    @Test
    void deleteIfPresentOrSave_present() {
        final Phone phone = target.createAndSavePhone();
        when(repository.findById(phone.getId())).thenReturn(Optional.of(phone));
        target.deleteIfPresentOrSave(phone);

        verify(repository).findById(phone.getId());
        verify(repository).delete(phone.getId());
        verify(repository, times(0)).save(phone);
    }

    @Test
    void deleteIfPresentOrSave_noPhones() {
        final Phone phone = target.createAndSavePhone();
        target.deleteIfPresentOrSave(phone);

        verify(repository).findById(phone.getId());
        verify(repository, times(0)).delete(phone.getId());
        verify(repository).save(phone);
    }

    @Test
    void deletePhoneMoreThen_present() {
        final Phone phone = target.createAndSavePhone();
        final Phone phone1 = target.createAndSavePhone();
        when(repository.findById(phone.getId())).thenReturn(Optional.of(phone));
        target.deletePhoneMoreThen(phone.getId(), 1);

        verify(repository).findById(phone.getId());
    }

    @Test
    void deletePhoneMoreThen_noPhones() {
        final Phone phone = target.createAndSavePhone();
        target.deletePhoneMoreThen(phone.getId(), 2);

        verify(repository).findById(phone.getId());
        verify(repository, times(0)).delete(phone.getId());
    }

    @Test
    void findByIdOrCreateRandomOptional_present() {
        final Phone phone = target.createAndSavePhone();
        when(repository.findById(phone.getId())).thenReturn(Optional.of(phone));

        Optional<Phone> foundedBallOptional = target.findByIdOrCreateRandomOptional(phone.getId());
        verify(repository).findById(phone.getId());
        Assertions.assertEquals(phone.getId(), foundedBallOptional.get().getId());
    }

    @Test
    void findByIdOrCreateRandomOptional_noPhones() {
        final Phone phone = target.createAndSavePhone();
        Optional<Phone> foundedBallOptional = target.findByIdOrCreateRandomOptional(phone.getId());
        verify(repository).findById(phone.getId());
        Assertions.assertNotEquals(phone.getId(), foundedBallOptional.get().getId());
    }

    @Test
    void findById_present() {
        final Phone phone = target.createAndSavePhone();
        when(repository.findById(phone.getId())).thenReturn(Optional.of(phone));
        Product foundedBall = target.findById(phone.getId());

        verify(repository).findById(phone.getId());
        Assertions.assertEquals(phone.getId(), foundedBall.getId());
    }

    @Test
    void findById_noPhones() {
        final Phone phone = target.createAndSavePhone();
        when(repository.findById(phone.getId())).thenReturn(Optional.empty());
        Assertions.assertThrows(IllegalArgumentException.class, () -> target.findById(phone.getId()));
        verify(repository).findById(phone.getId());
    }

    @Test
    void findById_CallRealMethod() {
        repository = mock(PhoneRepository.class);
        when(repository.findById("123")).thenCallRealMethod();
        Assertions.assertThrows(IllegalArgumentException.class, () -> target.findById("123"));
    }
}