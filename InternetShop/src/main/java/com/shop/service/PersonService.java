package com.shop.service;

import com.shop.model.Customer;

import java.util.Random;

public class PersonService {
    private static final Random RANDOM = new Random();

    public Customer generateRandomCustomer() {
        Customer customer = new Customer();
        customer.setAge(RANDOM.nextInt(6, 100));
        customer.setEmail(generateRandomEmail());
        return customer;
    }

    private static String generateRandomEmail() {
        String allowedChars  = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder email = new StringBuilder();
        while (email.length() < 10) {
            int index = (int) (RANDOM.nextFloat() * allowedChars.length());
            email.append(allowedChars.charAt(index));
        }
        email.append(RANDOM.nextInt(96));
        email.append("@mail.com");
        return email.toString();
    }
}
