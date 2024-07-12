package com.aticlesports.itemsports.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql = "UPDATE user SET deleted = true WHERE id=?")
public class Rent {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Products products;

    @NonNull
    private Date dateini;

    @NonNull
    private  Date dateend;

    @NonNull
    private int pricetot;

}
