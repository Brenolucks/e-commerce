package com.brenolucks.e_commerce.service.product;

import com.brenolucks.e_commerce.domain.dto.product.ProductRequest;
import com.brenolucks.e_commerce.domain.dto.product.ProductResponse;
import com.brenolucks.e_commerce.domain.mapper.product.ProductMapper;
import com.brenolucks.e_commerce.exceptions.product.ProductExist;
import com.brenolucks.e_commerce.exceptions.product.ProductNotFound;
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
                .ifPresent(p -> { throw new ProductExist("Product already exists!"); });

        var product = productMapper.toEntity(productRequest);
        return productMapper.toProductResponse(productRepository.save(product));
    }

    @Override
    public ProductResponse updateProduct(ProductRequest productRequest, Long productId) {
        var product = productRepository.findProductById(productId)
                .orElseThrow(() -> new ProductNotFound("Product not found!"));

        //implement test for this ifs
        if (productRequest.name() != null) product.setName(productRequest.name());
        if (productRequest.description() != null) product.setDescription(productRequest.description());
        if (productRequest.price() != null) product.setPrice(productRequest.price());
        if (productRequest.quantity() != 0) product.setQuantity(productRequest.quantity());
        if (productRequest.productCategory() != null) product.setProductCategory(productRequest.productCategory());
        if (productRequest.store() != null) product.setStore(productRequest.store());

        return productMapper.toProductResponse(productRepository.save(product));
    }
}
