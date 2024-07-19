package com.aticlesports.itemsports.services.implement;

import com.aticlesports.itemsports.DTO.ProductDTO;
import com.aticlesports.itemsports.entities.Products;
import com.aticlesports.itemsports.entities.Stores;
import com.aticlesports.itemsports.repositories.ProductRepository;
import com.aticlesports.itemsports.repositories.StoresRepository;
import com.aticlesports.itemsports.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService implements IProductService {

    @Autowired
    private StoresRepository storesRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public ResponseEntity<?> createProduct(ProductDTO productDTO, String email){

        Optional<Stores> storeOptional = storesRepository.findByEmail(email);
        if(storeOptional.isEmpty()){
            System.out.println("Store not found for email: " + email);
            return ResponseEntity.badRequest().build();
        }

        Stores store = storeOptional.get();
        Products product = new Products();
        product.setName(productDTO.getName());
        product.setCategory(productDTO.getCategory());
        product.setPricexday(productDTO.getPricexday());
        product.setAmount(productDTO.getAmount());
        product.setStore(store);

        // Verifica el producto antes de guardar
        System.out.println("Saving product: " + product);
        productRepository.save(product);

        return ResponseEntity.ok().build();
    }

}
