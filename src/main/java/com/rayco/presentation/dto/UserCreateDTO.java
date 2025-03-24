package com.rayco.presentation.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserCreateDTO {

    @NotNull
    private String name;

    @NotNull
    private String email;

    @NotNull
    private String password;

}
