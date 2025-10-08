package com.brenolucks.e_commerce.exceptions.product;

public class ProductNotFound extends RuntimeException {
    public ProductNotFound(String message) {
        super(message);
    }
}
