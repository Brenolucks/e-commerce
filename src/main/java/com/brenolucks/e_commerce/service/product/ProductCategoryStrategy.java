package com.brenolucks.e_commerce.service.product;

import com.brenolucks.e_commerce.domain.enums.ProductType;
import com.brenolucks.e_commerce.domain.model.Product;

public interface ProductCategoryStrategy {
    void setCategoryBasedOnType(Product product, ProductType productType);
}
