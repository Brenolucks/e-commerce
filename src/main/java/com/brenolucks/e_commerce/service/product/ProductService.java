package com.brenolucks.e_commerce.service.product;

import com.brenolucks.e_commerce.domain.dto.product.ProductRequest;
import com.brenolucks.e_commerce.domain.dto.product.ProductResponse;

public interface ProductService {
    ProductResponse registerProduct(ProductRequest productRequest);
}
