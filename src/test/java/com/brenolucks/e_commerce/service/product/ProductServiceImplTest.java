package com.brenolucks.e_commerce.service.product;

import com.brenolucks.e_commerce.domain.dto.product.ProductRequest;
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

        productServiceImpl.registerProduct(request);

        verify(productMapper, times(1)).toEntity(request);
        verify(productRepository, times(1)).save(product);
        verify(productMapper, times(1)).toProductResponse(product);
    }

    @Test
    void shouldThrowExceptionWhenProductAlreadyExists() {
        var request = new ProductRequest("Samsung S25 Ultra", "Test description", new BigDecimal(6700),
                100, ProductCategory.ELETRONICS, store);

        when(productRepository.findProductByNameAndStore_Id(request.name(), request.store().getId())).thenReturn(Optional.ofNullable(product));

        assertThrows(RuntimeException.class, () -> productServiceImpl.registerProduct(request));

        verify(productRepository, times(1)).findProductByNameAndStore_Id(request.name(), request.store().getId());
    }
}