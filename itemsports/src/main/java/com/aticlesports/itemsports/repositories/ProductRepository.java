package com.aticlesports.itemsports.repositories;

import com.aticlesports.itemsports.entities.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Products, Long> {
}
