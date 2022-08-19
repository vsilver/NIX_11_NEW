package com.command;

import com.model.product.*;
import com.model.product.laptop.Laptop;
import com.repository.TVRepository;
import com.service.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class Delete implements Command{
    private static final ProductService<Phone> PHONE_SERVICE =  PhoneService.getInstance();
    private static final ProductService<TV> TV_SERVICE = new TVService(new TVRepository());
    private static final ProductService<Laptop> LAPTOP_SERVICE = LaptopService.getInstance();
    private static final ProductService<Headphone> HEADPHONE_SERVICE = HeadphoneService.getInstance();
    private static final BufferedReader BUFFERED_READER = new BufferedReader(new InputStreamReader(System.in));

    @Override
    public void execute() throws IOException {
        System.out.println("What do you want to Delete:");
        ProductType[] types = ProductType.values();
        List<String> names = UtilEnumToList.getNamesOfType(types);
        int userInput = UserInputUtil.getUserInput(names, types.length);
        switch (types[userInput]) {
            case PHONE -> delete(PHONE_SERVICE);
            case LAPTOP -> delete(LAPTOP_SERVICE);
            case TV -> delete(TV_SERVICE);
            case HEADPHONE -> delete(HEADPHONE_SERVICE);
        }
    }

    private void delete(ProductService<? extends Product> service) {
        while (true) {
            System.out.println("Enter product ID");
            try {
                String id = BUFFERED_READER.readLine();
                service.findById(id);
                service.delete(id);
                return;
            } catch (IllegalArgumentException e) {
                System.out.println("Wrong ID. Try again");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
