package com.rayco.presentation.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Builder
@Data
public class UniversityDTO {

    private Long id;
    private Double averageRank;
    private Double latitude;
    private Double longitude;
    private Set<EntryTypesDTO> entryTypes;

    // Adicionar construtores, getters e setters, se necessário
}
