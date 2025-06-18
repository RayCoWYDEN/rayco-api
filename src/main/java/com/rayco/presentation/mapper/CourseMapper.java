package com.rayco.presentation.mapper;

import com.rayco.domain.entity.Course;
import com.rayco.presentation.dto.CourseAutocompleteDTO;
import com.rayco.presentation.dto.CourseDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CourseMapper {
    CourseAutocompleteDTO toAutocompleteDTO(Course courses);
    Course toEntity(CourseDTO dto);
    List<CourseAutocompleteDTO> toListAutocompleteDTO(List<Course> courses);
}
