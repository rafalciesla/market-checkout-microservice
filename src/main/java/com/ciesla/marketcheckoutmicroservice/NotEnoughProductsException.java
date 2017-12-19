package com.ciesla.marketcheckoutmicroservice;

public class NotEnoughProductsException extends Exception {
    public NotEnoughProductsException(String message) {
        super(message);
    }
}
