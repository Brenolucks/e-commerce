package com.brenolucks.e_commerce.service.product;

import com.brenolucks.e_commerce.domain.dto.product.ProductRequest;
import com.brenolucks.e_commerce.domain.dto.product.ProductRequestUpdate;
import com.brenolucks.e_commerce.domain.dto.product.ProductResponse;
import com.brenolucks.e_commerce.domain.mapper.product.ProductMapper;
import com.brenolucks.e_commerce.domain.model.Product;
import com.brenolucks.e_commerce.exceptions.product.ProductExist;
import com.brenolucks.e_commerce.exceptions.product.ProductNotFound;
import com.brenolucks.e_commerce.repository.Product.ProductRepository;
import com.brenolucks.e_commerce.utils.product.ProductUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductMapper productMapper;
    private final ProductRepository productRepository;
    private final ProductUtils productUtils;

    public ProductServiceImpl(ProductMapper productMapper, ProductRepository productRepository,
                              ProductUtils productUtils) {
        this.productMapper = productMapper;
        this.productRepository = productRepository;
        this.productUtils = productUtils;
    }
    
    @Override
    public ProductResponse registerProduct(ProductRequest productRequest) {
        productRepository.findProductByName(productRequest.name())
                .ifPresent(p -> { throw new ProductExist("Product already exists!"); });

        var product = productMapper.toEntity(productRequest);
        productUtils.createProductCategoryStrategyMap()
                .get(productRequest.productType())
                .setCategoryBasedOnType(product, product.getProductType());

        return productMapper.toProductResponse(productRepository.save(product));
    }

    @Override
    public ProductResponse updateProduct(ProductRequestUpdate productRequest, Long productId) {
        var product = productRepository.findProductById(productId)
                .orElseThrow(() -> new ProductNotFound("Product not found!"));
        product.updateFrom(productRequest);
        return productMapper.toProductResponse(productRepository.save(product));
    }

    @Override
    public String deleteProduct(Long productId) {
        var product = productRepository.findProductById(productId)
                .orElseThrow(() -> new ProductNotFound("Product not found!"));

        productRepository.deleteById(product.getId());
        return String.format("Product: %, deleted with success!", productId);
    }

    @Override
    public List<ProductResponse> listAllProducts() {
        var products = productRepository.findAll();
        return products
                .stream()
                .map(productMapper::toProductResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ProductResponse listProductById(Long productId) {
        var product = productRepository.findProductById(productId)
                .orElseThrow(() -> new ProductNotFound("Product not found!"));

        return productMapper.toProductResponse(product);
    }
}
