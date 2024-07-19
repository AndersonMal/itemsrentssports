package com.aticlesports.itemsports.controllers;

import com.aticlesports.itemsports.DTO.ProductDTO;
import com.aticlesports.itemsports.jwt.JwtUtil;
import com.aticlesports.itemsports.services.IProductService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

        // Verifica el email extraído del token
        System.out.println("Email from token: " + email);

        return productService.createProduct(productDTO, email);
    }


}
