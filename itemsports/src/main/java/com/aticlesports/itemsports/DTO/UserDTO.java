package com.aticlesports.itemsports.DTO;

import com.aticlesports.itemsports.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private String name;
    private String email;
    private String password;

}
