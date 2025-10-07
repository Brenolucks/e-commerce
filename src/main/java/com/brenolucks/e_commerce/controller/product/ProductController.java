package com.brenolucks.e_commerce.controller.product;

import com.brenolucks.e_commerce.domain.dto.product.ProductRequest;
import com.brenolucks.e_commerce.domain.dto.product.ProductResponse;
import com.brenolucks.e_commerce.service.product.ProductServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    private final ProductServiceImpl productServiceImpl;

    public ProductController(ProductServiceImpl productServiceImpl) {
        this.productServiceImpl = productServiceImpl;
    }

    @PostMapping("/create")
    public ResponseEntity<ProductResponse> createProduct(@RequestBody ProductRequest productRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productServiceImpl.registerProduct(productRequest));
    }
}
