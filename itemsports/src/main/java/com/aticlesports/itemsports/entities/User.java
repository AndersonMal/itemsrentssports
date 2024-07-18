package com.aticlesports.itemsports.entities;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql = "UPDATE user SET deleted = true WHERE id=?")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String name;

    @Column(unique = true)
    private String email;

    @NonNull
    private  String password;

    @NonNull
    private  int number;

    @NonNull
    @Enumerated(EnumType.STRING)
    private Role role;

}
