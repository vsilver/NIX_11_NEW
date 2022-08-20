package com.shop;

import com.shop.model.Customer;
import com.shop.service.PersonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PersonServiceTest {
    private PersonService personService;

    @BeforeEach
    void setUp() {
        personService = new PersonService();
    }

    @Test
    void generateRandomCustomer() {
        Customer customer = personService.generateRandomCustomer();

        assertNotNull(customer);
    }
}
