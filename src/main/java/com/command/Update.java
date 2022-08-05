package com.command;

import com.model.product.*;
import com.model.product.laptop.Laptop;
import com.repository.TVRepository;
import com.service.*;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class Update implements Command{

    private static final ProductService<Phone> PHONE_SERVICE =  PhoneService.getInstance();
    private static final ProductService<TV> TV_SERVICE = new TVService(new TVRepository());
    private static final ProductService<Laptop> LAPTOP_SERVICE = LaptopService.getInstance();
    private static final ProductService<Headphone> HEADPHONE_SERVICE = HeadphoneService.getInstance();
    private static final BufferedReader BUFFERED_READER = new BufferedReader(new InputStreamReader(System.in));

    @Override
    public void execute() {
        System.out.println("What do you want to update:");
        try {
            ProductType[] types = ProductType.values();
            List<String> names = UtilEnumToList.getNamesOfType(types);
            int userInput = UserInputUtil.getUserInput(names, types.length);
            switch (types[userInput]) {
                case PHONE -> update(PHONE_SERVICE);
                case LAPTOP -> update(LAPTOP_SERVICE);
                case TV -> update(TV_SERVICE);
                case HEADPHONE -> update(HEADPHONE_SERVICE);
                default -> throw new IllegalStateException("Unknown type");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void update(ProductService<? extends Product> service) {
        while (true) {
            System.out.println("Enter product ID");
            try {
                String id = BUFFERED_READER.readLine();
                Product product = service.findById(id);
                updateTitle(product);
                updatePrice(product);
                updateCount(product);
               // service.update(product);
                return;
            } catch (IllegalArgumentException e) {
                System.out.println("Wrong ID. Try again");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void updateCount(Product product) throws IOException {
        System.out.println("Enter new count");
        String count = BUFFERED_READER.readLine();
        if (StringUtils.isNumeric(count)) {
            product.setCount(Integer.parseInt(count));
        } else {
            System.out.println("Wrong input");
            updateCount(product);
        }
    }

    private void updatePrice(Product product) throws IOException {
        System.out.println("Enter new Price");
        String price = BUFFERED_READER.readLine();
        if (StringUtils.isNumeric(price)) {
            product.setPrice(Long.parseLong(price));
        } else {
            System.out.println("Wrong input");
            updatePrice(product);
        }
    }

    private void updateTitle(Product product) throws IOException {
        System.out.println("Enter new Title");
        String title = BUFFERED_READER.readLine();
        product.setTitle(title);
    }
}

