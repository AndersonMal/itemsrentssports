package com.aticlesports.itemsports.services;

import com.aticlesports.itemsports.DTO.ProductDTO;
import org.springframework.http.ResponseEntity;

public interface IProductService {;

    ResponseEntity<?> createProduct(ProductDTO productDTO, String email, String authHeader);

    ResponseEntity<?> GetAllProducts();

    ResponseEntity<?> UpdateProduct(ProductDTO productDTO, Long id);

    ResponseEntity<?> DeleteProduct(Long id);
}
