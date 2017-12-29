package com.epam.preprod.patsera.shop.exception;

public class TransactionInterruptedException extends Exception {

    public TransactionInterruptedException(String message) {
        super(message);
    }

    public TransactionInterruptedException(String message, Throwable cause) {
        super(message, cause);
    }
}
