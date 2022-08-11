package com.shop.utils;

import com.shop.exception.IncorrectStringException;
import com.shop.service.ProductFactory;
import com.shop.model.product.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParserCSV {
    private static final String MESSAGE = "Invalid input";

    private ParserCSV() {
    }

    public static List<Product> parseCSVToProducts(List<String> lines) {
        String[] fields = lines.get(0).split(",");
        Map<String, String> result = new HashMap<>();
        List<Product> products = new ArrayList<>();
        lines.stream()
                .skip(1)
                .map(l -> l.split(","))
                .forEach(values -> {
                    for (int i = 0; i < values.length; i++) {
                        result.put(fields[i], values[i]);
                    }
                    if (result.containsValue("")) {
                        try {
                            throw new IncorrectStringException(MESSAGE);
                        } catch (IncorrectStringException e) {
                            System.out.println(e.getMessage());
                        }
                    } else {
                        products.add(ProductFactory.createProductFromMap(result));
                    }

                });
        return products;
    }
}
