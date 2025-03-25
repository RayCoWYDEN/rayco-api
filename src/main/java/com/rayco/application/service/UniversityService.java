package com.rayco.application.service;

import com.rayco.application.component.DistanceComponent;
import com.rayco.domain.repository.UniversityRepository;
import com.rayco.presentation.dto.UniversityDTO;
import com.rayco.domain.entity.University;
import com.rayco.presentation.mapper.UniversityMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UniversityService {
    private final UniversityRepository repository;
    private final UniversityMapper mapper;
    private final DistanceComponent distanceComponent;

    public Page<UniversityDTO> listAll(boolean needCalcDistance, double latitude, double longitude, Pageable pageable) {
        Page<University> page = repository.findAll(pageable);
        List<UniversityDTO> dtos =  mapper.toListDTO(page.getContent());

        if(needCalcDistance)
            buildDistances(dtos, latitude, longitude);

        return new PageImpl<>(
                dtos,
                pageable,
                page.getTotalElements()
        );
    }

    private List<UniversityDTO> buildDistances(List<UniversityDTO> dtos, double latitude, double longitude){
        for(var university : dtos){
            double distance = distanceComponent.calcDistanceInKM(university.getLatitude(),
                    university.getLongitude(), latitude, longitude);

            university.setDistance(distance);
        }

        return  dtos;
    }

}
