package com.aticlesports.itemsports.services.implement;

import com.aticlesports.itemsports.DTO.ProductDTO;
import com.aticlesports.itemsports.entities.Products;
import com.aticlesports.itemsports.entities.Rent;
import com.aticlesports.itemsports.entities.User;
import com.aticlesports.itemsports.jwt.JwtUtil;
import com.aticlesports.itemsports.repositories.ProductRepository;
import com.aticlesports.itemsports.repositories.RentRepository;
import com.aticlesports.itemsports.repositories.StoresRepository;
import com.aticlesports.itemsports.repositories.UserRepository;
import com.aticlesports.itemsports.services.IRentService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class RentService implements IRentService {

    @Autowired
    private RentRepository rentRepository;

    @Autowired
    private StoresRepository storesRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    private final JwtUtil jwtUtil;

    public RentService(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }


    @Override
    public ResponseEntity<?> createRent(ProductDTO productDTO, int quantity, String authHeader){

            String token = authHeader.substring(7);
            String email = jwtUtil.extractUsername(token);
            Optional<User> userOptional = userRepository.findByEmail(email);
            if (userOptional.isEmpty()) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not found");
            }

            User user = userOptional.get();

            Optional<Products> product = productRepository.findById(productDTO.getId_product());
            if (product.isEmpty()) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Product not found");
            }

            Products products = product.get();
            if (products.getAmount() < quantity) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Quantity not sufficient");
            }else{
                Rent rent = new Rent();
                rent.setProducts(products);
                rent.setDateini(LocalDate.now());
                rent.setUser(user);rent.setProducts(products);
                rent.setDateend(LocalDate.now().plusDays(7));
                rent.setPricetot((int) (products.getPricexday()*quantity));
                products.setAmount(products.getAmount() - quantity);
                productRepository.save(products);

                Rent newRent = rentRepository.save(rent);

                return ResponseEntity.ok().body(newRent);
            }

    }

}
