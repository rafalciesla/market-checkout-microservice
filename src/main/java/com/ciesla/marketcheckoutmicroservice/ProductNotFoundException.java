package com.ciesla.marketcheckoutmicroservice;

public class ProductNotFoundException extends Exception {

    public ProductNotFoundException(String message) {
        super(message);
    }
}
