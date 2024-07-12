package com.aticlesports.itemsports.services;

import com.aticlesports.itemsports.DTO.UserDTO;
import org.springframework.http.ResponseEntity;

public interface IUserService {
    ResponseEntity<?> registerUser(UserDTO userDTO);
}
