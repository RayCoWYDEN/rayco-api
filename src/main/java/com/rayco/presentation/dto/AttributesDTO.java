package com.rayco.presentation.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AttributesDTO {
    private Long id;
    private String name;
}
