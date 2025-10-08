package com.brenolucks.e_commerce.service.product;

import com.brenolucks.e_commerce.domain.dto.product.ProductRequest;
import com.brenolucks.e_commerce.domain.dto.product.ProductResponse;
import com.brenolucks.e_commerce.domain.enums.ProductCategory;
import com.brenolucks.e_commerce.domain.mapper.product.ProductMapper;
import com.brenolucks.e_commerce.domain.model.Product;
import com.brenolucks.e_commerce.domain.model.Store;
import com.brenolucks.e_commerce.repository.Product.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {
    private Product product;
    private Store store;

    @Mock
    ProductRepository productRepository;
    @Mock
    ProductMapper productMapper;
    @InjectMocks
    ProductServiceImpl productServiceImpl;

    @BeforeEach
    void init() {
        store = new Store();
        store.setId(1L);
        store.setName("Store 1");

        product = new Product();
        product.setId(1L);
        product.setName("product name");
        product.setDescription("test product");
        product.setPrice(BigDecimal.valueOf(1.0));
        product.setQuantity(3);
        product.setProductCategory(ProductCategory.ELETRONICS);
        product.setStore(store);


    }
    @Test
    void shouldRegisterProductWithSuccess() {
        var request = new ProductRequest("Samsung S25 Ultra", "Test description", new BigDecimal(6700),
                100, ProductCategory.ELETRONICS, new Store());

        when(productMapper.toEntity(request)).thenReturn(product);
        when(productRepository.save(product)).thenReturn(product);
        when(productMapper.toProductResponse(product)).thenReturn(new ProductResponse(product.getName(),
                product.getDescription(), product.getPrice(), product.getQuantity(), product.getProductCategory(),
                product.getStore()));

        productServiceImpl.registerProduct(request);

        verify(productMapper, times(1)).toEntity(request);
        verify(productRepository, times(1)).save(product);
        verify(productMapper, times(1)).toProductResponse(product);
    }

    @Test
    void shouldThrowExceptionWhenProductAlreadyExists() {
        var request = new ProductRequest("Samsung S25 Ultra", "Test description", new BigDecimal(6700),
                100, ProductCategory.ELETRONICS, store);

        when(productRepository.findProductByNameAndStore_Id(request.name(), request.store().getId())).thenReturn(Optional.of(product));

        assertThrows(RuntimeException.class, () -> productServiceImpl.registerProduct(request));

        verify(productRepository, times(1)).findProductByNameAndStore_Id(request.name(), request.store().getId());
    }

    @Test
    void shouldUpdateProductWithSuccess() {
        var request = new ProductRequest("Samsung S25 Ultra", "Test description", new BigDecimal(6700),
                100, ProductCategory.ELETRONICS, store);

        when(productRepository.findProductById(1L)).thenReturn(Optional.of(product));
        when(productRepository.save(any(Product.class))).thenReturn(product);
        when(productMapper.toProductResponse(any(Product.class))).thenReturn(null);

        productServiceImpl.updateProduct(request, 1L);

        verify(productRepository, times(1)).findProductById(1L);
        verify(productRepository, times(1)).save(any(Product.class));
        verify(productMapper, times(1)).toProductResponse(any(Product.class));
    }

    @Test
    void shouldThrowExceptionWhenProductNotFound() {
        var request = new ProductRequest("Samsung S25 Ultra", "Test description", new BigDecimal(6700),
                100, ProductCategory.ELETRONICS, store);

        when(productRepository.findProductById(anyLong())).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> productServiceImpl.updateProduct(request, anyLong()));
        verify(productRepository, times(1)).findProductById(anyLong());
    }

    //TODO implements a test for every if in update method
}