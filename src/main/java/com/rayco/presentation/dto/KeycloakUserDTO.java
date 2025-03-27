package com.rayco.presentation.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class KeycloakUserDTO {
    private String email;

    private AttributesDTO attributes;

    @Builder.Default
    private boolean enabled = true;

    @Builder.Default
    private boolean emailVerified = true;

    private List<CredentialRepresentation> credentials;
}