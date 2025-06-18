package com.rayco.presentation.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CourseAutocompleteDTO {
    private Long id;
    private String name;
}
