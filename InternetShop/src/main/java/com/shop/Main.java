package com.shop;

import com.shop.controller.Controller;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            new Controller().run();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
