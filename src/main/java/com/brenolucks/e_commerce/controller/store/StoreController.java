package com.brenolucks.e_commerce.controller.store;

import com.brenolucks.e_commerce.domain.dto.store.StoreRequest;
import com.brenolucks.e_commerce.domain.dto.store.StoreResponse;
import com.brenolucks.e_commerce.service.store.StoreServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/store")
public class StoreController {
    private final StoreServiceImpl storeService;

    public StoreController(StoreServiceImpl storeService) {
        this.storeService = storeService;
    }

    @PostMapping("/create")
    public ResponseEntity<StoreResponse> createStore(@RequestBody StoreRequest storeRequest) {
        return  ResponseEntity.status(HttpStatus.CREATED).body(storeService.createStore(storeRequest));
    }
}
