package com.aticlesports.itemsports.controllers;

import com.aticlesports.itemsports.DTO.LoginDTO;
import com.aticlesports.itemsports.DTO.UserDTO;
import com.aticlesports.itemsports.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserDTO userDTO) {

        return userService.saveUser(userDTO);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {
        return userService.loginUser(loginDTO);
    }



}
