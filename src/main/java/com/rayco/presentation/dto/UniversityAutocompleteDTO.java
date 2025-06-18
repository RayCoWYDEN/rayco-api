package com.rayco.presentation.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UniversityAutocompleteDTO {
    private Long id;
    private String name;
}
