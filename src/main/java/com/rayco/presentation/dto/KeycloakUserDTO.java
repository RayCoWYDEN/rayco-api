package com.rayco.presentation.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class KeycloakUserDTO {

    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private boolean enabled;
    private List<CredentialRepresentation> credentials;
}