package com.epam.preprod.patsera.shop.exception;

public class SavingImageException extends Exception {
    public SavingImageException() {
        super();
    }

    public SavingImageException(String message) {
        super(message);
    }

    public SavingImageException(String message, Throwable cause) {
        super(message, cause);
    }
}
