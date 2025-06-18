package com.rayco.application.service;

import com.rayco.domain.entity.Course;
import com.rayco.domain.repository.CourseRepository;
import com.rayco.presentation.dto.CourseAutocompleteDTO;
import com.rayco.presentation.dto.UniversityAutocompleteDTO;
import com.rayco.presentation.mapper.CourseMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CourseService {
    private final CourseRepository repository;
    private final CourseMapper mapper;

    public List<CourseAutocompleteDTO> listAutocomplete(){
        List<Course> courses = repository.findAll();
        return mapper.toListAutocompleteDTO(courses);
    }

}
