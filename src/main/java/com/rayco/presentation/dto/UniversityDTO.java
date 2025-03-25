package com.rayco.presentation.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class UniversityDTO {
    private Long id;
    private String name;
    private double averageRank;
    private double latitude;
    private double longitude;
    private double distance;
    private boolean privateInstitution;
    private Set<EntryTypesDTO> entryTypes;
}
