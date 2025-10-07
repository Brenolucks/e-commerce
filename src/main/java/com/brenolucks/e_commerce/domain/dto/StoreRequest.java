package com.brenolucks.e_commerce.domain.dto;

import com.brenolucks.e_commerce.domain.model.Address;
import com.brenolucks.e_commerce.domain.model.Product;

import java.util.List;

public record StoreRequest(String name, Address address, String phoneNumber, String email,
                           List<Product> products) {
}
