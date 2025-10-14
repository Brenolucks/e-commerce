package com.brenolucks.e_commerce.domain.model;

import com.brenolucks.e_commerce.domain.dto.product.ProductRequestUpdate;
import com.brenolucks.e_commerce.domain.enums.ProductCategory;
import com.brenolucks.e_commerce.domain.enums.ProductType;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "tb_product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private int quantity;
    @Enumerated(EnumType.STRING)
    private ProductType productType;
    @Enumerated(EnumType.STRING)
    private ProductCategory productCategory;
   //private String imageUrl;
    @Builder
    private Product(String name, String description, BigDecimal price, int quantity, ProductType productType, ProductCategory productCategory) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.productType = productType;
        this.productCategory = productCategory;
    }

    public void updateFrom(ProductRequestUpdate request) {
        if (request.name() != null) {
            if (request.name().isBlank()) {
                throw new IllegalArgumentException("Name cannot be blank");
            }
            this.name = request.name();
        }

        if (request.description() != null) {
            this.description = request.description();
        }

        if (request.price() != null) {
            if (request.price().compareTo(BigDecimal.ZERO) <= 0) {
                throw new IllegalArgumentException("Price must be positive");
            }
            this.price = request.price();
        }

        if (request.quantity() != null) {
            if (request.quantity() < 0) {
                throw new IllegalArgumentException("Quantity cannot be negative");
            }
            this.quantity = request.quantity();
        }

        if (request.productType() != null) {
            this.productType = request.productType();
        }
    }
}
