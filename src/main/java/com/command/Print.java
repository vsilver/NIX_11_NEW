package com.command;

import com.model.product.*;
import com.service.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class Print implements Command {
    private static final ProductService<Phone> PHONE_SERVICE = PhoneService.getInstance();
    private static final ProductService<TV> TV_SERVICE = TVService.getInstance();
    private static final ProductService<Laptop> LAPTOP_SERVICE = LaptopService.getInstance();
    private static final ProductService<Headphone> HEADPHONE_SERVICE = HeadphoneService.getInstance();

    @Override
    public void execute() throws IOException {
        System.out.println("What do you want to print:");
        /*final ProductType[] values = ProductType.values();
        int userType = -1;
        do {
            for (int i = 0; i < values.length; i++) {
                System.out.printf("%d) %s%n", i, values[i].name());
            }
            int input = SCANNER.nextInt();
            if (input >= 0 && input < values.length) {
                userType = input;
            }
        } while (userType == -1);

        switch (values[userType]) {
            case PHONE -> PHONE_SERVICE.printAll();
            default -> {
                throw new IllegalArgumentException("Unknown ProductType " + values[userType]);
            }
        }*/

        ProductType[] planeTypes = ProductType.values();
        List<String> names = UtilEnumToList.getNamesOfType(planeTypes);
        int userInput = UserInputUtil.getUserInput(names, planeTypes.length);
        switch (planeTypes[userInput]) {
            case PHONE -> print(PHONE_SERVICE);
            case TV -> print(TV_SERVICE);
            case LAPTOP -> print(LAPTOP_SERVICE);
            case HEADPHONE -> print(HEADPHONE_SERVICE);
        }
    }

    private void print(ProductService<? extends Product> service) {
        if (service.findAll().isEmpty()) {
            System.out.println("\nDatabase is empty");
        }
        else {
            System.out.println("\nDatabase: ");
            service.findAll().forEach(System.out::println);
        }
    }
}

