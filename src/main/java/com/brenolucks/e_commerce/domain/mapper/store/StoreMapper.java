package com.brenolucks.e_commerce.domain.mapper.store;

import com.brenolucks.e_commerce.domain.dto.store.StoreRequest;
import com.brenolucks.e_commerce.domain.dto.store.StoreResponse;
import com.brenolucks.e_commerce.domain.model.Store;
import org.springframework.stereotype.Component;

@Component
public class StoreMapper {
    public Store toEntity(StoreRequest storeRequest) {
        Store store = new Store();
        store.setName(storeRequest.name());
        store.setAddress(storeRequest.address());
        store.setEmail(storeRequest.email());
        store.setPhoneNumber(storeRequest.phoneNumber());

        return store;
    }

    public StoreRequest toStoreRequest(Store store) {
        return new StoreRequest(store.getName(), store.getAddress(), store.getEmail(), store.getPhoneNumber());
    }

    public StoreResponse toStoreResponse(Store store) {
        return new StoreResponse(store.getName(), store.getAddress(), store.getEmail(), store.getPhoneNumber());
    }
}
