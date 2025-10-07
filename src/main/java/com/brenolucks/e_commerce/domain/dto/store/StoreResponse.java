package com.brenolucks.e_commerce.domain.dto.store;

import com.brenolucks.e_commerce.domain.model.Address;

public record StoreResponse(String name, Address address, String phoneNumber, String email) {
}
