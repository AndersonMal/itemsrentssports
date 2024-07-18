package com.aticlesports.itemsports.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TokenResponseDTO {

    private String email;
    private boolean email_verified;
    private String name;
    private String password;

}
