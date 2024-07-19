package com.aticlesports.itemsports.DTO;

import com.aticlesports.itemsports.entities.Location;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StoreDTO {

   private String name;
   private String email;
   private String password;
   private int number;
   private Location location;
   private long storeId;

}
