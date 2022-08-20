package com.command;

import com.model.product.*;
import com.model.product.laptop.Laptop;
import com.service.*;
import com.util.UserInputUtil;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Create implements Command{

    @Override
    public void execute() throws IOException {
        System.out.println("What do you want to Create:");
        try {
            ProductType[] types = ProductType.values();
            int productTypeIndex = UserInputUtil.getUserInput(Arrays.stream(types).map(Enum::name).toList());
            ProductFactory.createAndSave(types[productTypeIndex]);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
