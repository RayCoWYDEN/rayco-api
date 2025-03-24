package com.rayco.presentation.mapper;

import com.rayco.presentation.dto.UniversityDTO;
import com.rayco.domain.entity.University;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UniversityMapper {

    UniversityDTO toDTO(University university);

    List<UniversityDTO> toListDTO(List<University> universities);
}