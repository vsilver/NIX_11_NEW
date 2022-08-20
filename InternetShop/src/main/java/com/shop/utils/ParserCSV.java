package com.shop.utils;

import com.shop.exception.IncorrectStringException;
import com.shop.service.ProductFactory;
import com.shop.model.product.Product;

import java.util.*;
import java.util.stream.IntStream;

public class ParserCSV {
    private static final String MESSAGE = "Invalid input";

    private ParserCSV() {
    }

    public static List<Product> parseCSVToProducts(List<String> lines) {
        /*String[] fields = lines.get(0).split(",");
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
        return products;*/
        String[] headlines = lines.get(0).split(",");
        int typeIndex = Arrays.asList(headlines).indexOf("type");
        lines.remove(0);
        ProductServices[] productServices = ProductServices.values();
        List<Product> result = new ArrayList<>();

        lines.forEach(string -> {
            try {
                String[] values = string.split(",");
                if (Arrays.asList(values).contains("")) {
                    throw new IncorrectStringException("Incorrect line: " + string);
                }

                Map<String, Object> map = new HashMap<>();
                IntStream.range(0, values.length)
                        .forEach(index -> {
                            if (index != typeIndex) {
                                map.put(headlines[index], values[index]);
                            }
                        });

                Optional<ProductServices> object = Arrays.stream(productServices)
                        .filter(element -> element.getName().equals(values[typeIndex]))
                        .findFirst();

                object.ifPresent(service -> result.add(service.getCreatableFromMap().createProduct(map)));
            }
            catch (IncorrectStringException e) {
                System.out.println("\n" + e.getMessage());
            }
        });

        return result;
    }
}
