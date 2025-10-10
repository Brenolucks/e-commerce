package com.brenolucks.e_commerce.domain.mapper.product;

import com.brenolucks.e_commerce.domain.dto.product.ProductRequest;
import com.brenolucks.e_commerce.domain.dto.product.ProductResponse;
import com.brenolucks.e_commerce.domain.model.Product;
import org.springframework.stereotype.Component;

@Component()
public class ProductMapper {
    public Product toEntity(ProductRequest productRequest) {
        var product = new Product();
        product.setName(productRequest.name());
        product.setDescription(productRequest.description());
        product.setPrice(productRequest.price());
        product.setQuantity(productRequest.quantity());
        product.setProductType(productRequest.productType());
        return product;
    }

    public ProductRequest toProductRequest(Product product) {
        return new ProductRequest(product.getName(), product.getDescription(),
                product.getPrice(), product.getQuantity(), product.getProductType());
    }

    public ProductResponse toProductResponse(Product product) {
        return new ProductResponse(product.getName(), product.getDescription(),
                product.getPrice(), product.getQuantity(), product.getProductType(), product.getProductCategory());
    }
}
