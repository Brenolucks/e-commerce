package com.brenolucks.e_commerce.exceptions;

import com.brenolucks.e_commerce.domain.dto.product.ProductException;
import com.brenolucks.e_commerce.exceptions.product.ProductExist;
import com.brenolucks.e_commerce.exceptions.product.ProductNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalHandleException {
    @ExceptionHandler(ProductExist.class)
    public ResponseEntity<ProductException> handleProductExistException(ProductExist e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ProductException(
                HttpStatus.BAD_REQUEST,
                e.getMessage(),
                LocalDateTime.now()
        ));
    }

    @ExceptionHandler(ProductNotFound.class)
    public ResponseEntity<ProductException> handleProductExistException(ProductNotFound e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ProductException(
                HttpStatus.NOT_FOUND,
                e.getMessage(),
                LocalDateTime.now()
        ));
    }
}
