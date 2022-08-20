package com.shop.service;

import com.shop.model.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

public class PersonService {
    private static final Random RANDOM = new Random();
    private static final Logger LOGGER = LoggerFactory.getLogger(PersonService.class);

    public Customer generateRandomCustomer() {
        Customer customer = new Customer();
        String email = customer.getId().substring(0, 5).toLowerCase() + "@gmail.com";
        customer.setAge(RANDOM.nextInt(6, 100));
        customer.setEmail(email);
        int min = 14;
        int max = 99;
        int age = (int) Math.floor(min + (1 + (max - min))*Math.random());
        customer.setAge(age);
        return customer;
    }
}
