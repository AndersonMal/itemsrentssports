package com.aticlesports.itemsports.services;

import com.aticlesports.itemsports.DTO.ProductDTO;
import org.springframework.http.ResponseEntity;

public interface IProductService {;

    ResponseEntity<?> createProduct(ProductDTO productDTO, String email);

    ResponseEntity<?> GetAllProducts();

    ResponseEntity<?> UpdateProduct(ProductDTO productDTO, Long id);
}
