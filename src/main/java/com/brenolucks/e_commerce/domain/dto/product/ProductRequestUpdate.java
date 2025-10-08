package com.brenolucks.e_commerce.domain.dto.product;

import com.brenolucks.e_commerce.domain.enums.ProductCategory;
import jakarta.annotation.Nullable;

import java.math.BigDecimal;

public record ProductRequestUpdate(
        @Nullable String name, @Nullable String description, @Nullable BigDecimal price,
        int quantity, @Nullable ProductCategory productCategory
) {
}
