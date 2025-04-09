package com.rayco.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetUniversitiesParamsDTO {
    private boolean needCalcDistance;

    private double latitude;

    private double longitude;
}


