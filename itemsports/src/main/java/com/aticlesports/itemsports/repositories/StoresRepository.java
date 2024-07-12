package com.aticlesports.itemsports.repositories;

import com.aticlesports.itemsports.entities.Stores;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoresRepository extends JpaRepository<Stores, Long> {
}
