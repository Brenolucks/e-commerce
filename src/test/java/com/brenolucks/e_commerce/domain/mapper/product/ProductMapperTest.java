package com.brenolucks.e_commerce.domain.mapper.product;

import com.brenolucks.e_commerce.domain.dto.product.ProductRequest;
import com.brenolucks.e_commerce.domain.enums.ProductCategory;
import com.brenolucks.e_commerce.domain.model.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ProductMapperTest {
    @InjectMocks
    private ProductMapper mapper;

    @Test
    void shouldBeConvertProductRequestToProduct() {
        var productRequest = new ProductRequest("Product", "Product description", BigDecimal.valueOf(1000),
                100, ProductCategory.AUTOMOTIVE);

        var converted = mapper.toEntity(productRequest);

        assertNotNull(converted);
        assertEquals(productRequest.name(), converted.getName());
        assertEquals(productRequest.description(), converted.getDescription());
        assertEquals(productRequest.price(), converted.getPrice());
        assertEquals(productRequest.quantity(), converted.getQuantity());
        assertEquals(productRequest.productCategory(), converted.getProductCategory());
    }

    @Test
    void shouldBeConvertProductToProductRequest() {
        var product = new Product();
        product.setName("Product name");
        product.setDescription("Product description");
        product.setPrice(BigDecimal.valueOf(100));
        product.setQuantity(100);
        product.setProductCategory(ProductCategory.AUTOMOTIVE);

        var converted = mapper.toProductRequest(product);

        assertNotNull(converted);
        assertEquals(product.getName(), converted.name());
        assertEquals(product.getDescription(), converted.description());
        assertEquals(product.getPrice(), converted.price());
        assertEquals(product.getQuantity(), converted.quantity());
        assertEquals(product.getProductCategory(), converted.productCategory());
    }

    @Test
    void shouldBeConvertProductToProductResponse() {
        var product = new Product();
        product.setName("Product name");
        product.setDescription("Product description");
        product.setPrice(BigDecimal.valueOf(100));
        product.setQuantity(100);
        product.setProductCategory(ProductCategory.AUTOMOTIVE);

        var converted = mapper.toProductResponse(product);

        assertNotNull(converted);
        assertEquals(product.getName(), converted.name());
        assertEquals(product.getDescription(), converted.description());
        assertEquals(product.getPrice(), converted.price());
        assertEquals(product.getQuantity(), converted.quantity());
        assertEquals(product.getProductCategory(), converted.productCategory());
    }
}
