package com.aticlesports.itemsports.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "products")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Products {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id_product;

    @NonNull
    private String name;

    private String description;

    @NonNull
    private Long pricexday;

    @Enumerated(EnumType.STRING)
    private Category category;

    @NonNull
    private int amount;


    @ManyToOne
    @JoinColumn(name = "store_id", nullable = false)
    private Stores store;

}
