package com.shop.exception;

import java.io.IOException;

public class IncorrectStringException extends IOException {
    public IncorrectStringException(String message){
        super(message);
    }
}
