package com.model;

import com.model.product.Product;

import java.util.Comparator;

public class ProductComparator <T extends Product> implements Comparator<T> {
    @Override
    /*public int compare(T first, T second) {
        if (second.getPrice() == first.getPrice()) {
            if (first.getTitle().equals(second.getTitle())) {
                return Integer.compare(first.getCount(), second.getCount());
            }
            return first.getTitle().compareTo(second.getTitle());
        }
        return Double.compare(second.getPrice(), first.getPrice());
    }*/
    public int compare(T o1, T o2) {
        if (o2.getPrice() == o1.getPrice()) {
            if (o1.getTitle().equals(o2.getTitle())) {
                return Integer.compare(o1.getCount(), o2.getCount());
            }
            return o1.getTitle().compareTo(o2.getTitle());
        }
        return Long.compare(o2.getPrice(), o1.getPrice());
    }
}
