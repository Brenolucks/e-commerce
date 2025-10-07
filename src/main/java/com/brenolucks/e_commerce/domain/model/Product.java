package com.brenolucks.e_commerce.domain.model;

import com.brenolucks.e_commerce.domain.enums.ProductCategory;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
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
    private ProductCategory productCategory;
    @ManyToOne(fetch =  FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;
    //private String imageUrl;
}
