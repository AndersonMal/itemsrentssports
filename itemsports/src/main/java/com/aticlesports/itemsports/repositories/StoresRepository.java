package com.aticlesports.itemsports.repositories;

import com.aticlesports.itemsports.entities.Stores;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StoresRepository extends JpaRepository<Stores, Long> {

    Optional<Stores> findByEmail(String email);
}
