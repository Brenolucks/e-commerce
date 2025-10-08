package com.brenolucks.e_commerce.exceptions.product;

public class ProductExist extends RuntimeException {
  public ProductExist(String message) {
    super(message);
  }
}
