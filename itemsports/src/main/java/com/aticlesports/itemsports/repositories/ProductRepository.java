package com.aticlesports.itemsports.repositories;

import com.aticlesports.itemsports.entities.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Products, Long> {

    Optional<Products> findById(Long id);

}
