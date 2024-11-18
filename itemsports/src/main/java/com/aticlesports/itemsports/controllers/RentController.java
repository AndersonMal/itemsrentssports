package com.aticlesports.itemsports.controllers;


import com.aticlesports.itemsports.DTO.ProductDTO;
import com.aticlesports.itemsports.entities.User;
import com.aticlesports.itemsports.jwt.JwtUtil;
import com.aticlesports.itemsports.services.IRentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Optional;
@RestController
@RequestMapping("/rent")
public class RentController {

    @Autowired
    private IRentService rentService;

    private JwtUtil jwtUtil;

    public RentController(IRentService rentService){this.rentService = rentService;}

    @PostMapping("/createrent")
    public ResponseEntity<?> registerRent(@RequestBody ProductDTO rent, @RequestParam int quantity,
                                          @RequestHeader("Authorization") String authHeader){
        return rentService.createRent(rent, quantity, authHeader);
    }

}
