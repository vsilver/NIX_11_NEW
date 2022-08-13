package com.example.model;

import lombok.Setter;

@Setter
public class ProductBundle extends Product {

    protected int amount;

    public void setAmount(int amount) {
        this.amount = amount;
    }

}
