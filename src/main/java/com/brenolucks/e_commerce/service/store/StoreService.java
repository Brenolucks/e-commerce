package com.brenolucks.e_commerce.service.store;

import com.brenolucks.e_commerce.domain.dto.store.StoreRequest;
import com.brenolucks.e_commerce.domain.dto.store.StoreResponse;

public interface StoreService {
    StoreResponse createStore(StoreRequest storeRequest);
}
