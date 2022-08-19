package com.command;

import com.model.product.*;
import com.model.product.laptop.Laptop;
import com.service.*;

import java.io.IOException;
import java.util.List;

public class Create implements Command{

    private static final ProductService<Phone> PHONE_SERVICE = PhoneService.getInstance();
    private static final ProductService<TV> TV_SERVICE = TVService.getInstance();
    private static final ProductService<Laptop> LAPTOP_SERVICE = LaptopService.getInstance();
    private static final ProductService<Headphone> HEADPHONE_SERVICE = HeadphoneService.getInstance();

    @Override
    public void execute() throws IOException {
        System.out.println("What do you want to Create:");
        ProductType[] types = ProductType.values();
            List<String> names = UtilEnumToList.getNamesOfType(types);
            int userInput = UserInputUtil.getUserInput(names, types.length);
            switch (types[userInput]) {
                case PHONE -> PHONE_SERVICE.save(PHONE_SERVICE.createProduct());
                case TV -> TV_SERVICE.save(TV_SERVICE.createProduct());
                case LAPTOP -> LAPTOP_SERVICE.save(LAPTOP_SERVICE.createProduct());
                case HEADPHONE -> HEADPHONE_SERVICE.save(HEADPHONE_SERVICE.createProduct());
        }
    }
}
