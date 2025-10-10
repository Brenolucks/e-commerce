package com.brenolucks.e_commerce.service.product.strategy;

import com.brenolucks.e_commerce.domain.enums.ProductCategory;
import com.brenolucks.e_commerce.domain.enums.ProductType;
import com.brenolucks.e_commerce.domain.model.Product;
import com.brenolucks.e_commerce.service.product.ProductCategoryStrategy;

public class AutomotiveStrategy implements ProductCategoryStrategy {
    @Override
    public void setCategoryBasedOnType(Product product, ProductType productType) {
        product.setProductCategory(ProductCategory.AUTOMOTIVE);
    }
}
