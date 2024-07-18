package com.aticlesports.itemsports.services;

import com.aticlesports.itemsports.DTO.LoginDTO;
import com.aticlesports.itemsports.DTO.UserDTO;
import org.springframework.http.ResponseEntity;

public interface IUserService {
    ResponseEntity<?> saveUser(UserDTO userDTO);
    ResponseEntity<?> loginUser(LoginDTO loginDTO);
}
