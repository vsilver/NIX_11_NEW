package com.shop.service;

import com.shop.model.*;
import com.shop.model.product.Product;
import com.shop.model.product.ProductType;
import com.shop.model.television.Television;

import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

public class ProductFactory {
    /*private static final String TYPE = "type";
    private static final String MODEL = "model";
    private static final String PRICE = "price";
    private static final String SCREEN_TYPE = "screen type";
    private static final String SERIES = "series";
    private static final String COUNTRY = "country";
    private static final String DIAGONAL = "diagonal";
    private static final String MESSAGE = "Unknown type ";*/

    private ProductFactory() {
    }

    /*public static Product createProductFromMap(Map<String, String> fields) {
        Function<Map<String, String>, Product> mapToProduct = map -> {
            switch (map.get(TYPE).toLowerCase()) {
                case "telephone" -> {
                    Telephone telephone = new Telephone(ProductType.TELEPHONE);
                    telephone.setModel(map.get(MODEL));
                    telephone.setPrice(Long.parseLong(map.get(PRICE)));
                    telephone.setScreenType(map.get(SCREEN_TYPE));
                    telephone.setSeries(map.get(SERIES));
                    return telephone;
                }
                case "television" -> {
                    Television television = new Television(ProductType.TELEVISION);
                    television.setCountry(map.get(COUNTRY));
                    television.setDiagonal(map.get(DIAGONAL));
                    television.setScreenType(map.get(SCREEN_TYPE));
                    television.setPrice(Long.parseLong(map.get(PRICE)));
                    television.setSeries(map.get(SERIES));
                    return television;
                }
                default -> throw new IllegalArgumentException(MESSAGE + map.get(TYPE));
            }
        };
        return mapToProduct.apply(fields);
    }*/
    public static Product createProductFromMap(Map<String, Object> hashMap) {
        String product = (String) hashMap.get("type");
        switch (product) {
            case "Telephone" -> {
                return Optional.of(new Telephone(
                        (String) hashMap.get("series"),
                        ScreenType.valueOf((String) hashMap.get("screen type")),
                        Double.parseDouble(hashMap.get("price").toString()),
                        (String) hashMap.get("model"))
                );
            }
            case "Television" -> {
                return Optional.of(new Television(
                        (String) hashMap.get("series"),
                        ScreenType.valueOf((String) hashMap.get("screen type")),
                        Double.parseDouble(hashMap.get("price").toString()),
                        Double.parseDouble(hashMap.get("diagonal").toString()),
                        (String) hashMap.get("country"))
                );
            }
            default -> {
                return Optional.empty();


            }
        }
    }
}
