package com.aticlesports.itemsports.controllers;

import com.aticlesports.itemsports.DTO.UserDTO;
import com.aticlesports.itemsports.services.IUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private IUserService userService;

    private  UserDTO userDTO;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestParam String token) {
        return userService.registerUser(userDTO);
    }


}
