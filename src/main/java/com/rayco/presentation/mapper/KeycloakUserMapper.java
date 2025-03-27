package com.rayco.presentation.mapper;

import com.rayco.domain.entity.User;
import com.rayco.presentation.dto.CredentialRepresentation;
import com.rayco.presentation.dto.KeycloakUserDTO;
import com.rayco.presentation.dto.UserCreateDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface KeycloakUserMapper {
    @Mapping(source = "password", target = "credentials", qualifiedByName = "mapCredentials")
    @Mapping(source = "user.id", target = "attributes.id")
    @Mapping(source = "user.name", target = "attributes.name")
    KeycloakUserDTO toKeycloakUserDTO(User user, String password);

    @Named("mapCredentials")
    default List<CredentialRepresentation> mapCredentials(String password){
        CredentialRepresentation credential = CredentialRepresentation.builder()
                .type("password")
                .value(password)
                .temporary(false)
                .build();

        return List.of(credential);
    }

}