package com.rayco.application.service;

import com.rayco.domain.repository.UniversityRepository;
import com.rayco.presentation.dto.UniversityDTO;
import com.rayco.domain.entity.University;
import com.rayco.presentation.mapper.UniversityMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UniversityService {

    private final UniversityRepository repository;
    private final UniversityMapper mapper;


    public Page<UniversityDTO> listAll(Pageable pageable) {
        Page<University> page = repository.findAll(pageable);

        return new PageImpl<>(
                mapper.toListDTO(page.getContent()),
                pageable,
                page.getTotalElements()
        );
    }
}
