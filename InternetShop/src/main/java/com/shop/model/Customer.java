package com.shop.model;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class Customer {
    protected String  id;
    protected String email;
    protected int age;

    public Customer() {
        id = UUID.randomUUID().toString();
    }

    public void setID(String id) {
        this.id = id;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", email=" + email +
                ", age=" + age +
                '}';
    }

}
