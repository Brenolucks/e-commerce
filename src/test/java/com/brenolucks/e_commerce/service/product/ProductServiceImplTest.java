package com.brenolucks.e_commerce.service.product;

import com.brenolucks.e_commerce.domain.dto.product.ProductRequest;
import com.brenolucks.e_commerce.domain.dto.product.ProductRequestUpdate;
import com.brenolucks.e_commerce.domain.dto.product.ProductResponse;
import com.brenolucks.e_commerce.domain.enums.ProductCategory;
import com.brenolucks.e_commerce.domain.mapper.product.ProductMapper;
import com.brenolucks.e_commerce.domain.model.Product;
import com.brenolucks.e_commerce.repository.Product.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {
    private Product product;
    @Mock
    ProductRepository productRepository;
    @Mock
    ProductMapper productMapper;
    @InjectMocks
    ProductServiceImpl productServiceImpl;

    @BeforeEach
    void init() {
        product = new Product();
        product.setId(1L);
        product.setName("product name");
        product.setDescription("test product");
        product.setPrice(BigDecimal.valueOf(1.0));
        product.setQuantity(3);
        product.setProductCategory(ProductCategory.ELETRONICS);


    }
    @Test
    void shouldRegisterProductWithSuccess() {
        var request = new ProductRequest("Samsung S25 Ultra", "Test description", new BigDecimal(6700),
                100, ProductCategory.ELETRONICS);

        when(productMapper.toEntity(request)).thenReturn(product);
        when(productRepository.save(product)).thenReturn(product);
        when(productMapper.toProductResponse(product)).thenReturn(new ProductResponse(product.getName(),
                product.getDescription(), product.getPrice(), product.getQuantity(), product.getProductCategory()));

        productServiceImpl.registerProduct(request);

        verify(productMapper, times(1)).toEntity(request);
        verify(productRepository, times(1)).save(product);
        verify(productMapper, times(1)).toProductResponse(product);
    }

    @Test
    void shouldThrowExceptionWhenProductAlreadyExists() {
        var request = new ProductRequest("Samsung S25 Ultra", "Test description", new BigDecimal(6700),
                100, ProductCategory.ELETRONICS);

        when(productRepository.findProductByName(request.name())).thenReturn(Optional.of(product));

        assertThrows(RuntimeException.class, () -> productServiceImpl.registerProduct(request));

        verify(productRepository, times(1)).findProductByName(request.name());
    }

    @Test
    void shouldUpdateProductWithSuccess() {
        var request = new ProductRequestUpdate("Samsung S25 Ultra", "Test description", new BigDecimal(6700),
                100, ProductCategory.ELETRONICS);

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
        var request = new ProductRequestUpdate("Samsung S25 Ultra", "Test description", new BigDecimal(6700),
                100, ProductCategory.ELETRONICS);

        when(productRepository.findProductById(anyLong())).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> productServiceImpl.updateProduct(request, anyLong()));
        verify(productRepository, times(1)).findProductById(anyLong());
    }

    @Test
    void shouldDeleteProductWithSuccess() {
        when(productRepository.findProductById(1L)).thenReturn(Optional.of(product));
        doNothing().when(productRepository).deleteById(1L);

        var productDeleted = productServiceImpl.deleteProduct(1L);

        assertEquals(String.format("Product: %, deleted with success!", 1L), productDeleted);
        verify(productRepository, times(1)).findProductById(1L);
        verify(productRepository, times(1)).deleteById(1L);
    }

    @Test
    void shouldThrowExceptionWhenDeleteProductNotFound() {
        when(productRepository.findProductById(1L)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> productServiceImpl.deleteProduct(1L));
        verify(productRepository, times(1)).findProductById(1L);
    }

    @Test
    void shouldListAllProductsWithSuccess() {
        var product = new Product(1L, "Product 1", "Description 1", BigDecimal.valueOf(100),
                10, ProductCategory.ELETRONICS);
        var product2 = new Product(2L, "Product 2", "Description 2", BigDecimal.valueOf(200),
                10, ProductCategory.ELETRONICS);


        when(productRepository.findAll()).thenReturn(List.of(product, product2));

        var productResponse = new ProductResponse("Product 1", "Description 1", BigDecimal.valueOf(100),
                10, ProductCategory.ELETRONICS);
        var productResponse2 = new ProductResponse("Product 2", "Description 2", BigDecimal.valueOf(200),
                10, ProductCategory.ELETRONICS);

        when(productMapper.toProductResponse(product)).thenReturn(productResponse);
        when(productMapper.toProductResponse(product2)).thenReturn(productResponse2);

        var productResponseList = productServiceImpl.listAllProducts();

        assertNotNull(productResponseList);
        verify(productRepository, times(1)).findAll();
        verify(productMapper, times(1)).toProductResponse(product);
        verify(productMapper, times(1)).toProductResponse(product2);
    }

    @Test
    void shouldListProductByIdWithSuccess() {
        when(productRepository.findProductById(1L)).thenReturn(Optional.of(product));
        when(productMapper.toProductResponse(product)).thenReturn(new ProductResponse(product.getName(),
                product.getDescription(), product.getPrice(), product.getQuantity(), product.getProductCategory()));

        productServiceImpl.listProductById(1L);

        verify(productRepository, times(1)).findProductById(1L);
        verify(productMapper, times(1)).toProductResponse(product);
    }

    @Test
    void shouldThrowExceptionWhenListProductByIdNotFound() {
        when(productRepository.findProductById(1L)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> productServiceImpl.listProductById(1L));
        verify(productRepository, times(1)).findProductById(1L);
    }
}