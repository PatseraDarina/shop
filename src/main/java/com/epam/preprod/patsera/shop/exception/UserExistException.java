package com.epam.preprod.patsera.shop.exception;

public class UserExistException extends Exception {

    public UserExistException(String s) {
        super(s);
    }

    public UserExistException(String message, Throwable cause) {
        super(message, cause);
    }
}
