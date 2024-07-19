package com.aticlesports.itemsports.DTO;

import com.aticlesports.itemsports.entities.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    private String name;
    private String description;
    private Long pricexday;
    private Category category;
    private int amount;
    private Long storeId;

}
