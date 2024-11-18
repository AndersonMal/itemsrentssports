package com.aticlesports.itemsports.services;

import com.aticlesports.itemsports.DTO.ProductDTO;
import com.aticlesports.itemsports.DTO.StoreDTO;
import com.aticlesports.itemsports.DTO.UserDTO;
import org.springframework.http.ResponseEntity;

public interface IRentService {

    ResponseEntity<?> createRent(ProductDTO productDTO, int quantity, String authHeader);
}
