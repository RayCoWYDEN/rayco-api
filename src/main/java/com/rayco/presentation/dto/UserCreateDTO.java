package com.rayco.presentation.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserCreateDTO {

    @NotNull
    private String name;

    @NotNull
    private String email;

    @NotNull
    private String password;

}
