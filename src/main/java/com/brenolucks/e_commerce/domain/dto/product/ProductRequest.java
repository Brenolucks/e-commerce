package com.brenolucks.e_commerce.domain.dto.product;

import com.brenolucks.e_commerce.domain.enums.ProductCategory;
import com.brenolucks.e_commerce.domain.enums.ProductType;

import java.math.BigDecimal;

public record ProductRequest(String name, String description, BigDecimal price,
                             int quantity, ProductType productType) {
}
