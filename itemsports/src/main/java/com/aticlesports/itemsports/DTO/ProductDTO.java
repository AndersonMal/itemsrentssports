package com.aticlesports.itemsports.DTO;

import com.aticlesports.itemsports.entities.Category;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

        @NotNull(message = "name is required.")
        private String name;
        private String description;
        private Long pricexday;

        @NotNull(message = "category is required.")
        private Category category;
        @NotNull(message = "Amount is required.")
        @Min(value = 1, message = "Amount must be greater than zero.")
        private Integer amount;
        private Long storeId;

}
