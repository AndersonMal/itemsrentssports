package com.aticlesports.itemsports.seeder;

import com.aticlesports.itemsports.entities.Role;
import com.aticlesports.itemsports.entities.User;
import com.aticlesports.itemsports.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class Seeders implements CommandLineRunner {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        System.out.println("Running Seeders...");
        loadUsers();
        System.out.println("Seeders executed.");
    }


    private void loadUsers() {

            User user1 = new User(1L, "Anderson", "anderfusion@gmail.com", "1234", 123456, Role.USER);
            User user2 = new User(2L, "Yesid", "isseisama202@gmail.com","23422" ,12345,  Role.ADMIN);
            userRepository.save(user1);
            userRepository.save(user2);
            System.out.println("Users loaded successfully.");
        }

}
