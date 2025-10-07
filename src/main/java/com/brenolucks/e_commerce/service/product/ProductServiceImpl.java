package com.brenolucks.e_commerce.service.product;

import com.brenolucks.e_commerce.domain.dto.product.ProductRequest;
import com.brenolucks.e_commerce.domain.dto.product.ProductResponse;
import com.brenolucks.e_commerce.domain.mapper.product.ProductMapper;
import com.brenolucks.e_commerce.repository.Product.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductMapper productMapper;
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductMapper productMapper, ProductRepository productRepository) {
        this.productMapper = productMapper;
        this.productRepository = productRepository;
    }
    
    @Override
    public ProductResponse registerProduct(ProductRequest productRequest) {
        productRepository.findProductByNameAndStore_Id(productRequest.name(), productRequest.store().getId())
                .ifPresent(p -> { throw new RuntimeException("Product already exists!"); });

        var product = productMapper.toEntity(productRequest);
        return productMapper.toProductResponse(productRepository.save(product));
    }
}
