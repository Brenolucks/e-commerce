package com.brenolucks.e_commerce.domain.dto;

import com.brenolucks.e_commerce.domain.enums.State;

public record AddressRequest(String street, String number, String complement, String city,
                             State state, String zipCode) {
}
