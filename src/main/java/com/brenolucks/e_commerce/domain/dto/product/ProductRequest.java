package com.brenolucks.e_commerce.domain.dto.product;

import com.brenolucks.e_commerce.domain.enums.ProductCategory;
import com.brenolucks.e_commerce.domain.model.Store;

import java.math.BigDecimal;

public record ProductRequest(String name, String description, BigDecimal price,
                             int quantity, ProductCategory productCategory, Store store) {
}
