package com.aticlesports.itemsports.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql = "UPDATE user SET deleted = true WHERE id=?")
public class Rent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @ManyToOne
    @JoinColumn(name = "store_id", nullable = false)
    private  Stores stores;

    @NonNull
    private LocalDate dateini;

    @NonNull
    private LocalDate dateend;

    @NonNull
    private int pricetot;

}
