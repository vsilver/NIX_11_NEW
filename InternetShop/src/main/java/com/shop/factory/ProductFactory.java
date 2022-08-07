package com.shop.factory;

import com.shop.model.*;

import java.util.Random;

public class ProductFactory {
    private static final Random RANDOM = new Random();
    private static ProductFactory instance;

    private ProductFactory() {
    }

    public static ProductFactory getInstance() {
        if (instance == null) {
            instance = new ProductFactory();
        }

        return instance;
    }

    public Product generateRandomProduct() {
        if (RANDOM.nextBoolean()) {
            Telephone telephone = new Telephone();
            telephone.setSeries(RANDOM.nextLong());
            telephone.setScreenType(ScreenType.OLED);
            telephone.setPrice(RANDOM.nextDouble());
            telephone.setModel(RANDOM.nextInt());
            return telephone;
        }
        else {
            Television television = new Television();
            television.setSeries(RANDOM.nextLong());
            television.setScreenType(ScreenType.IPS);
            television.setPrice(RANDOM.nextDouble());
            television.setDiagonal(RANDOM.nextInt());
            television.setCountry(Country.USA);
            return television;
        }
    }
}
