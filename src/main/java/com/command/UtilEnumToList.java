package com.command;

import com.model.product.ProductType;

import java.util.ArrayList;
import java.util.List;

public final class UtilEnumToList {

    public static List<String> getNamesOfType(ProductType[] planeTypes) {
        List<String> names = new ArrayList<>(planeTypes.length);
        for (ProductType type : planeTypes) {
            names.add(type.name());
        }
        return names;
    }

    public static List<String> getNamesOfType(Commands[] commands) {
        List<String> names = new ArrayList<>(commands.length);
        for (Commands type : commands) {
            names.add(type.name());
        }
        return names;
    }
}
