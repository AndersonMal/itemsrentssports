package com.aticlesports.itemsports.controllers;


import com.aticlesports.itemsports.DTO.LoginDTO;
import com.aticlesports.itemsports.DTO.StoreDTO;
import com.aticlesports.itemsports.services.IStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/store")
public class StoreController {

    @Autowired
    private IStoreService storeService;

    public StoreController(IStoreService storeService){
        this.storeService = storeService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerStore(@RequestBody StoreDTO storeDTO){
        return storeService.createStore(storeDTO);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {
        return storeService.loginStore(loginDTO.getEmail(), loginDTO.getPassword());
    }

}
