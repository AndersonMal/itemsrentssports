package com.aticlesports.itemsports.services.implement;

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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

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

        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setName(userDTO.getName());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setRole(Role.USER);
        

        List<String> roles = new ArrayList<>();
        roles.add(user.getRole().name());

        User newUser =     userRepository.save(user);

        return ResponseEntity.ok().body(newUser);
    }

    @Override
    public ResponseEntity<?> loginUser(String email, String password) {
        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            // Verifica si la contraseña es correcta
            if (passwordEncoder.matches(password, user.getPassword())) {
                // Genera un token para el usuario con el email, rol y nombre
                String token = jwtUtil.generateToken(user.getEmail(), List.of(user.getRole().name()), user.getName());
                return ResponseEntity.ok().body(new Token(token));
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Contraseña incorrecta");
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
    }

}
