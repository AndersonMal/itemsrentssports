package com.aticlesports.itemsports.entities;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "store")
public class Stores {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NonNull
    private String name;

    @Column(unique = true)
    private String email;

    @NonNull
    private String password;

    @NonNull
    private int number;

    @NonNull
    @Enumerated(EnumType.STRING)
    private Location location;

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Products> products;


}
