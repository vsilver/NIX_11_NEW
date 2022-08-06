package com.service;

import com.model.product.laptop.Laptop;
import com.model.product.Manufacturer;
import com.model.product.laptop.LaptopType;
import com.repository.LaptopRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatcher;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

class LaptopServiceTest {
    private LaptopService target;

    private LaptopRepository repository;

    @BeforeEach
    void setUp() {
        repository = mock(LaptopRepository.class);
        target = new LaptopService(repository);
    }

    @Test
    void createAndSaveLaptops_negativeCount() {
        assertThrows(IllegalArgumentException.class, () -> target.createAndSave(-1));
    }

    @Test
    void createAndSaveLaptops_zeroCount() {
        assertThrows(IllegalArgumentException.class, () -> target.createAndSave(0));
    }

    @Test
    void createAndSaveLaptops() {
        target.createAndSave(3);
        verify(repository, times(1)).saveAll(anyList());
    }

    @Test
    void printAll() {
        target.printAll();
        verify(repository).getAll();
    }

    @Test
    void saveLaptop_matchers() {
        final Laptop laptop = new Laptop();
        target.saveLaptop(laptop);

        verify(repository).save(argThat(new ArgumentMatcher<Laptop>(){
            @Override
            public boolean matches(Laptop laptop) {
                return laptop.getTitle().equals("Title");
            }
        }));
    }

    @Test
    void saveLaptop_zeroCount() {
        final Laptop laptop = new Laptop();
        target.saveLaptop(laptop);

        ArgumentCaptor<Laptop> argument = ArgumentCaptor.forClass(Laptop.class);
        verify(repository).save(argument.capture());
        assertEquals("Title", argument.getValue().getTitle());
        assertEquals(-1, argument.getValue().getCount());
    }

    @Test
    void getAll() {
        target.getAll();
        verify(repository).getAll();
    }

    @Test
    void findById_CallRealMethod() {
        when(target.findById(anyString())).thenCallRealMethod();
        Assertions.assertThrows(RuntimeException.class, () -> target.findById(anyString()));

    }
}