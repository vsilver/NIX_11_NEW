package com.shop.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Customer {
    protected int id;
    protected String email;
    protected int age;

    public void setID(int id) {
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
