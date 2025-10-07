package com.brenolucks.e_commerce.service.store;

import com.brenolucks.e_commerce.domain.dto.store.StoreRequest;
import com.brenolucks.e_commerce.domain.mapper.store.StoreMapper;
import com.brenolucks.e_commerce.domain.model.Address;
import com.brenolucks.e_commerce.domain.model.Store;
import com.brenolucks.e_commerce.repository.Store.StoreRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StoreServiceImplTest {
    private Store store;

    @Mock
    private StoreRepository storeRepository;

    @Mock
    private StoreMapper storeMapper;

    @InjectMocks
    private StoreServiceImpl storeService;

    @BeforeEach
    void init() {
        store = new Store();
        store.setId(1L);
        store.setName("Test Store");
        store.setAddress(new Address());
        store.setPhoneNumber("81999999999");
        store.setEmail("teste@gmail.com");
        store.setProducts(new ArrayList<>());
    }

    @Test
    public void shouldRegisterAStoreWithSuccess() {
        var request = new StoreRequest("Store Req", new Address(), "81999999999", "teste@gmail.com");

        when(storeMapper.toEntity(request)).thenReturn(store);
        when(storeRepository.save(store)).thenReturn(store);

        storeService.createStore(request);

        verify(storeMapper, times(1)).toEntity(request);
        verify(storeRepository, times(1)).save(store);
    }
}
