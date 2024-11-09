package com.aticlesports.itemsports.services.implement;

import com.aticlesports.itemsports.DTO.ProductDTO;
import com.aticlesports.itemsports.repositories.ProductRepository;
import com.aticlesports.itemsports.repositories.StoresRepository;
import com.aticlesports.itemsports.repositories.UserRepository;
import com.aticlesports.itemsports.services.IRentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Service
public class RentService implements IRentService {

    @Autowired
    private StoresRepository storesRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    private Double calculatePrice(ProductDTO productDTO, LocalDate initDate, LocalDate finalDate, Integer amount){
        long days = ChronoUnit.DAYS.between(initDate, finalDate);
        return (double) (productDTO.getPricexday() * days * amount);
    }

}
