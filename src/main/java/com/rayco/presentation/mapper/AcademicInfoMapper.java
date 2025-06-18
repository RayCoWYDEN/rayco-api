package com.rayco.presentation.mapper;

import com.rayco.domain.entity.AcademicInfo;
import com.rayco.domain.entity.Course;
import com.rayco.presentation.dto.AcademicInfoDTO;
import com.rayco.presentation.dto.CourseAutocompleteDTO;
import com.rayco.presentation.dto.CourseDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AcademicInfoMapper {
    AcademicInfo toEntity(AcademicInfoDTO dto);
}
