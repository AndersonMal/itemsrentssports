package com.aticlesports.itemsports.services.implement;

import com.aticlesports.itemsports.DTO.LoginDTO;
import com.aticlesports.itemsports.DTO.Token;
import com.aticlesports.itemsports.DTO.UserDTO;
import com.aticlesports.itemsports.entities.Role;
import com.aticlesports.itemsports.entities.User;
import com.aticlesports.itemsports.jwt.JwtUtil;
import com.aticlesports.itemsports.repositories.UserRepository;
import com.aticlesports.itemsports.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;


    private final JwtUtil jwtUtil;

    public UserService(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }


    @Override
    public ResponseEntity<?> saveUser(UserDTO userDTO) {
        Optional<User> existingUser = userRepository.findByEmail(userDTO.getEmail());
        if (existingUser.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("User already exists");
        }

        // Registrar el nuevo usuario
        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setName(userDTO.getName());
        user.setPassword(userDTO.getPassword());
        user.setRole(Role.USER);
        userRepository.save(user);

        List<String> roles = new ArrayList<>();
        roles.add(user.getRole().name());
        String jwtToken = jwtUtil.generateToken(user.getEmail(), roles, user.getName());

        Token tokenMessage = new Token();
        tokenMessage.setMessage(jwtToken);

        return ResponseEntity.status(HttpStatus.CREATED).body(tokenMessage);
    }
    @Override
    public ResponseEntity<?> loginUser(LoginDTO loginDTO) {
        Optional<User> user = userRepository.findByEmail(loginDTO.getEmail());
        if (user.isPresent() && user.get().getPassword().equals(loginDTO.getPassword())) {
            List<String> roles = new ArrayList<>();
            roles.add(user.get().getRole().name());

            String jwt = jwtUtil.generateToken(user.get().getEmail(), roles, user.get().getName());
            Token tokenMessage = new Token();
            tokenMessage.setMessage(jwt);
            return ResponseEntity.ok().body(tokenMessage);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
    }



}
