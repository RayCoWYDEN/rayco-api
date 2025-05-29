package com.rayco.presentation.mapper;

import com.rayco.domain.entity.EntryTypes;
import com.rayco.presentation.dto.EntryTypesDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EntryTypesMapper {

    EntryTypesDTO toDTO(EntryTypes entryTypes);

    List<EntryTypesDTO> toListDTO(List<EntryTypes> entryTypes);
}
