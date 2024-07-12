package com.aticlesports.itemsports.DTO;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    @NonNull
    private String userName;

    @NonNull
    private String email;

    @NonNull
    private String password;

}
