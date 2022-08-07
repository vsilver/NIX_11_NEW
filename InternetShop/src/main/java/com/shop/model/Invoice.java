package com.shop.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class Invoice {
    protected List<String> productList = new ArrayList<>();
    protected Customer customer;
    protected String type;
    protected LocalDateTime creatingTime;

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<String> getProductList() {
        return productList;
    }

    public void setProductList(List<String> productList) {
        this.productList = productList;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "productList=" + productList +
                ", customer=" + customer +
                ", type=" + type +
                ", creatingTime=" + creatingTime +
                '}';
    }

}
