package com.aticlesports.itemsports.controllers;

import com.aticlesports.itemsports.DTO.ProductDTO;
import com.aticlesports.itemsports.jwt.JwtUtil;
import com.aticlesports.itemsports.services.IProductService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private IProductService productService;

    private final JwtUtil jwtUtil;

    public ProductController(IProductService productService, JwtUtil jwtUtil){
            this.productService = productService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createProduct(@RequestBody ProductDTO productDTO, HttpServletRequest request){
        String token = request.getHeader("Authorization").substring(7);
        String email = jwtUtil.extractUsername(token);

        System.out.println("Email from token: " + email);

        return productService.createProduct(productDTO, email);
    }

    @GetMapping("/getall")
    public ResponseEntity<?> getAllproducts(){
        return productService.GetAllProducts();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Long id, @RequestBody ProductDTO productUpdateDTO) {
        try {
           productService.UpdateProduct(productUpdateDTO, id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id){
        try{
            productService.DeleteProduct(id);
            return ResponseEntity.ok().body("Deleted product successful");
        }catch (Exception e) {
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }
    }

}
