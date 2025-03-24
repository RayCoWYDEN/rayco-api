package com.rayco.presentation.mapper;

import com.rayco.presentation.dto.KeycloakUserDTO;
import com.rayco.presentation.dto.UserCreateDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface KeycloakUserMapper {
    UserCreateDTO toEntity(KeycloakUserDTO user);

}