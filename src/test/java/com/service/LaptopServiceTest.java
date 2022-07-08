package com.service;

import com.model.Laptop;
import com.model.Manufacturer;
import com.repository.LaptopRepository;
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
        assertThrows(IllegalArgumentException.class, () -> target.createAndSaveLaptops(-1));
    }

    @Test
    void createAndSaveLaptops_zeroCount() {
        assertThrows(IllegalArgumentException.class, () -> target.createAndSaveLaptops(0));
    }

    @Test
    void createAndSaveLaptops_zeroCount_Mock() {
        try {
            when(target.createAndSaveLaptops(0)).thenThrow(new IllegalArgumentException("Input is not valid!!"));
        } catch (Exception e){
            assertTrue(e instanceof IllegalArgumentException);
            assertEquals("Input is not valid!!",e.getMessage());
        }
    }

    @Test
    void createAndSaveLaptops() {
        target.createAndSaveLaptops(3);
        verify(repository, times(1)).saveAll(anyList());
    }

    @Test
    void printAll() {
        target.printAll();
        verify(repository).getAll();
    }

    @Test
    void saveLaptop_matchers() {
        final Laptop laptop = new Laptop("Title", 100, 1000.0,"Model" , Manufacturer.SAMSUNG);
        target.saveLaptop(laptop);

        verify(repository).save(argThat(new ArgumentMatcher<Laptop>(){
            @Override
            public boolean matches(Laptop laptop) {
                return laptop.getTitle().equals("Title");
            }
        }));
    }

    @Test
    void saveLaptop_call_real_method() {
        final Laptop laptop = new Laptop("Title", 100, 1000.0,"Model" , Manufacturer.SAMSUNG);
        when(target.saveLaptop(laptop)).thenCallRealMethod(laptop.getTitle().equals("Title"));
        assertEquals("Title", laptop.getTitle());
    }

    @Test
    void saveLaptop_zeroCount() {
        final Laptop laptop = new Laptop("Title", 0, 1000.0,"Model" , Manufacturer.SAMSUNG);
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
}