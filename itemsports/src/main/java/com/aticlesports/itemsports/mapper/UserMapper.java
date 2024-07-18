package com.aticlesports.itemsports.mapper;

import com.aticlesports.itemsports.DTO.UserDTO;
import com.aticlesports.itemsports.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.springframework.stereotype.Component;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
@Component
public interface UserMapper {

    User toEntity (UserDTO userDTO);
    UserDTO toDto (User user);

}
