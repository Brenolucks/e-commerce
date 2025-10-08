package com.brenolucks.e_commerce.controller.product;

import com.brenolucks.e_commerce.domain.dto.product.ProductRequest;
import com.brenolucks.e_commerce.domain.dto.product.ProductRequestUpdate;
import com.brenolucks.e_commerce.domain.dto.product.ProductResponse;
import com.brenolucks.e_commerce.domain.enums.ProductCategory;
import com.brenolucks.e_commerce.service.product.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductControllerTest {
    private ProductRequest productRequest;
    private ProductResponse productResponse;
    private ProductRequestUpdate productRequestUpdate;

    @Mock
    ProductServiceImpl productService;
    @InjectMocks
    ProductController productController;

    @BeforeEach
    void init() {
        productRequest = new ProductRequest("Product Req", "Description Req", BigDecimal.valueOf(100),
                100, ProductCategory.APPAREL);

        productResponse = new ProductResponse("Prodcut Res", "Description Res", BigDecimal.valueOf(100),
                100, ProductCategory.APPAREL);

        productRequestUpdate = new ProductRequestUpdate("Product Updated", null, null,
                0, null);
    }

    @Test
    void shouldCreateProductWithSuccess() throws Exception {
        when(productService.registerProduct(productRequest)).thenReturn(productResponse);

        var result = productController.createProduct(productRequest);

        assertEquals(productResponse, result.getBody());
        assertEquals(HttpStatus.CREATED, result.getStatusCode());

        verify(productService, times(1)).registerProduct(productRequest);
    }

    @Test
    void shouldUpdateAProductWithSuccess() throws Exception {
        when(productService.updateProduct(productRequestUpdate, 1L)).thenReturn(productResponse);

        var result = productController.updateProduct(productRequestUpdate, 1L);

        assertEquals(productResponse, result.getBody());
        assertEquals(HttpStatus.OK, result.getStatusCode());
        verify(productService, times(1)).updateProduct(productRequestUpdate, 1L);
    }

    @Test
    void shouldDeleteProductWithSuccess() throws Exception {
        when(productService.deleteProduct(1L)).thenReturn(String.format("Product: %d, deleted with success", 1L));

        var result = productController.deleteProduct(1L);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        verify(productService, times(1)).deleteProduct(1L);
    }

    @Test
    void shouldListAllProductsWithSuccess() throws Exception {
        when(productService.listAllProducts()).thenReturn(Arrays.asList(productResponse, productResponse));

        var result = productController.listAllProducts();

        assertEquals(HttpStatus.OK, result.getStatusCode());
        verify(productService, times(1)).listAllProducts();
    }

    @Test
    void shouldListProductById() throws Exception {
        when(productService.listProductById(1L)).thenReturn(productResponse);

        var result = productController.listProduct(1L);

        assertEquals(productResponse, result.getBody());
        assertEquals(HttpStatus.OK, result.getStatusCode());
        verify(productService, times(1)).listProductById(1L);
    }
}
