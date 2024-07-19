package com.aticlesports.itemsports.services;

import com.aticlesports.itemsports.DTO.StoreDTO;
import org.springframework.http.ResponseEntity;

public interface IStoreService {
    ResponseEntity<?> createStore(StoreDTO storeDTO);

    ResponseEntity<?> loginStore(String email, String password);
}
