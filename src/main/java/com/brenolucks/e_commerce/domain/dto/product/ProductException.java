package com.brenolucks.e_commerce.domain.dto.product;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public record ProductException(HttpStatus status, String message, LocalDateTime timestamp) {
}
