package com.brenolucks.e_commerce.service.store;

import com.brenolucks.e_commerce.domain.dto.store.StoreRequest;
import com.brenolucks.e_commerce.domain.dto.store.StoreResponse;
import com.brenolucks.e_commerce.domain.mapper.store.StoreMapper;
import com.brenolucks.e_commerce.repository.Store.StoreRepository;
import org.springframework.stereotype.Service;

@Service
public class StoreServiceImpl implements StoreService {
    private final StoreMapper storeMapper;
    private final StoreRepository storeRepository;

    public StoreServiceImpl(StoreMapper storeMapper, StoreRepository storeRepository) {
        this.storeMapper = storeMapper;
        this.storeRepository = storeRepository;
    }

    @Override
    public StoreResponse createStore(StoreRequest storeRequest) {
        return storeMapper.toStoreResponse(storeRepository.save(storeMapper.toEntity(storeRequest)));
    }
}
