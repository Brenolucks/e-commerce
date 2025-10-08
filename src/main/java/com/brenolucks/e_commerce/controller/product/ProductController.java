package com.brenolucks.e_commerce.controller.product;

import com.brenolucks.e_commerce.domain.dto.product.ProductRequest;
import com.brenolucks.e_commerce.domain.dto.product.ProductRequestUpdate;
import com.brenolucks.e_commerce.domain.dto.product.ProductResponse;
import com.brenolucks.e_commerce.service.product.ProductServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PatchMapping("/update/{id}")
    public ResponseEntity<ProductResponse> updateProduct(@RequestBody ProductRequestUpdate productRequest,
                                                         @PathVariable("id") Long productId) {
        return ResponseEntity.status(HttpStatus.OK).body(productServiceImpl.updateProduct(productRequest, productId));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable("id") Long productId) {
        productServiceImpl.deleteProduct(productId);
        return ResponseEntity.status(HttpStatus.OK).body(String.format("Product: %, deleted with success!", productId));
    }

    @GetMapping("/")
    public ResponseEntity<List<ProductResponse>> listAllProducts() {
        return ResponseEntity.status(HttpStatus.OK).body(productServiceImpl.listAllProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> listProduct(@PathVariable("id") Long productId) {
        return ResponseEntity.status(HttpStatus.OK).body(productServiceImpl.listProductById(productId));
    }
}
