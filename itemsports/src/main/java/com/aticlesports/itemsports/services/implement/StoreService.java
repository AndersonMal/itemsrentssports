package com.aticlesports.itemsports.services.implement;

import com.aticlesports.itemsports.DTO.StoreDTO;
import com.aticlesports.itemsports.DTO.Token;
import com.aticlesports.itemsports.entities.Stores;
import com.aticlesports.itemsports.jwt.JwtUtil;
import com.aticlesports.itemsports.repositories.StoresRepository;
import com.aticlesports.itemsports.services.IStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StoreService implements IStoreService {

    @Autowired
    private StoresRepository storesRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    private final JwtUtil jwtUtil;

    public StoreService(JwtUtil jwtUtil){
        this.jwtUtil = jwtUtil;
    }

    @Override
    public ResponseEntity<?> createStore(StoreDTO storeDTO) {

        Optional<Stores> store= storesRepository.findByEmail(storeDTO.getEmail());
        if (store.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Store already exists whit this email");
        }

        Stores stores = new Stores();
        stores.setEmail(storeDTO.getEmail());
        stores.setPassword(passwordEncoder.encode(storeDTO.getPassword()));
        stores.setName(storeDTO.getName());
        stores.setNumber(storeDTO.getNumber());
        stores.setLocation(storeDTO.getLocation());

        Stores newStore = storesRepository.save(stores);

        return ResponseEntity.ok().body(newStore);
    }

    @Override
    public ResponseEntity<?> loginStore(String email, String password) {
        Optional<Stores> storeOptional = storesRepository.findByEmail(email);
        if (storeOptional.isPresent()) {
            Stores store = storeOptional.get();
            System.out.println("Contraseña almacenada: " + store.getPassword());
            System.out.println("Contraseña ingresada: " + password);
            if (passwordEncoder.matches(password, store.getPassword())) {
                String token = jwtUtil.generateToken(store.getEmail(), List.of("STORE"), store.getName());
                return ResponseEntity.ok().body(new Token(token));
            } else {
                System.out.println("Contraseña incorrecta");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Contraseña incorrecta");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tienda no encontrada");
        }
    }

    @Override
    public ResponseEntity<?> GetAllStores(){
        List<Stores> listStores = storesRepository.findAll();

        return ResponseEntity.ok(listStores);
    }


}
