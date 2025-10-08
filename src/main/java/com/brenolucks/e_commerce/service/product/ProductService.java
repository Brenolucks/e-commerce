package com.brenolucks.e_commerce.service.product;

import com.brenolucks.e_commerce.domain.dto.product.ProductRequest;
import com.brenolucks.e_commerce.domain.dto.product.ProductRequestUpdate;
import com.brenolucks.e_commerce.domain.dto.product.ProductResponse;

import java.util.List;

public interface ProductService {
    ProductResponse registerProduct(ProductRequest productRequest);
    ProductResponse updateProduct(ProductRequestUpdate productRequest, Long productId);
    String deleteProduct(Long productId);
    List<ProductResponse> listAllProducts();
    ProductResponse listProductById(Long productId);
}
