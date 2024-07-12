package com.aticlesports.itemsports.services.implement;

import com.aticlesports.itemsports.DTO.UserDTO;
import com.aticlesports.itemsports.entities.User;
import com.aticlesports.itemsports.mapper.UserMapper;
import com.aticlesports.itemsports.repositories.UserRepository;
import com.aticlesports.itemsports.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Override
    public ResponseEntity<?> registerUser(UserDTO userDTO) {
        User user = userMapper.toEntity(userDTO);
        user = userRepository.save(user);
        return userMapper.toDto(user);
    }


}
