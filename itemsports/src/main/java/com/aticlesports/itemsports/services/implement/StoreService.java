package com.aticlesports.itemsports.services.implement;

import com.aticlesports.itemsports.DTO.StoreDTO;
import com.aticlesports.itemsports.entities.Stores;
import com.aticlesports.itemsports.repositories.StoresRepository;
import com.aticlesports.itemsports.services.IStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StoreService implements IStoreService {

    @Autowired
    private StoresRepository storesRepository;

    @Override
    public ResponseEntity<?> createStore(StoreDTO storeDTO) {

        Optional<Stores> store= storesRepository.findByEmail(storeDTO.getEmail());
        if (store.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Store already exists whit this email");
        }

        Stores stores = new Stores();
        stores.setEmail(storeDTO.getEmail());
        stores.setPassword(storeDTO.getPassword());
        stores.setName(storeDTO.getName());
        stores.setNumber(storeDTO.getNumber());
        stores.setLocation(storeDTO.getLocation());

        Stores newStore = storesRepository.save(stores);

        return ResponseEntity.ok().body(stores);
    }




}
