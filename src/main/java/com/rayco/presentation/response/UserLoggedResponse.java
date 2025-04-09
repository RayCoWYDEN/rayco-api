package com.rayco.presentation.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserLoggedResponse {
    private Long id;
    private String name;
    private String email;
    private TokenResponse token;
}
