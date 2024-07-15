package com.aticlesports.itemsports.jwt;


import com.aticlesports.itemsports.entities.User;
import com.aticlesports.itemsports.repositories.UserRepository;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Slf4j
@Service
public class CustomerDetailService implements UserDetailsService {

    @Autowired
    private UserRepository iUserRepository;
    @Getter
    private User userDetail;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> userDetail2 = iUserRepository.findByEmail(email);
        if(userDetail2.isPresent()){
            return new org.springframework.security.core.userdetails.User(userDetail2.get().getEmail(),"", new ArrayList<>());
        }else{
            throw new UsernameNotFoundException("Usuario no encontrado");
        }
    }
}
