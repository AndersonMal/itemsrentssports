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

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements IProductService {

    @Autowired
    private StoresRepository storesRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public ResponseEntity<?> createProduct(ProductDTO productDTO, String email, String authHeader){

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Authorization header is missing or invalid");
        }

        Optional<Stores> storeOptional = storesRepository.findByEmail(email);
        if(storeOptional.isEmpty()) {
            System.out.println("Store not found for email: " + email);
            return ResponseEntity.badRequest().build();
        }

        Stores store = storeOptional.get();
        Products product = new Products();
        product.setName(productDTO.getName());
        product.setCategory(productDTO.getCategory());
        product.setDescription(productDTO.getDescription());
        product.setPricexday(productDTO.getPricexday());
        product.setAmount(productDTO.getAmount());

        product.setStore(store);

        System.out.println("Saving product: " + product);
        productRepository.save(product);

        return ResponseEntity.ok().body("Successfully create product");
    }

    @Override
    public ResponseEntity<?> GetAllProducts() {
        List<Products> listProducts = productRepository.findAll();

        return ResponseEntity.ok(listProducts);
    }

    @Override
    public ResponseEntity<?> UpdateProduct(ProductDTO productDTO, Long id){
        Optional<Products> productOptional = productRepository.findById(id);
        if (productOptional.isEmpty()) {
            System.out.println("Store not found for id: " + id);
            return ResponseEntity.badRequest().build();
        }
        Products product = productOptional.get();
        if (productDTO.getName() != null) {
            product.setName(productDTO.getName());
        }
        if (productDTO.getDescription() != null) {
            product.setDescription(productDTO.getDescription());
        }
        if (productDTO.getPricexday() != null) {
            product.setPricexday(productDTO.getPricexday());
        }
        if (productDTO.getCategory() != null) {
            product.setCategory(productDTO.getCategory());
        }
        product.setAmount(productDTO.getAmount());
        productRepository.save(product);
        return ResponseEntity.ok().body(product);

    }

    @Override
    public ResponseEntity<?> DeleteProduct(Long id){
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return ResponseEntity.ok().body("Product deleted successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
        }
    }

}
