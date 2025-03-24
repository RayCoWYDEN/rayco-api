package com.rayco.presentation.dto;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CredentialRepresentation {
    private String type;
    private String value;
    private boolean temporary;
}
