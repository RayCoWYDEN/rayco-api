package com.rayco.application.service;

import com.rayco.application.component.DistanceComponent;
import com.rayco.domain.entity.FavoriteUniversity;
import com.rayco.domain.entity.University;
import com.rayco.domain.entity.User;
import com.rayco.domain.repository.FavoriteUniversitiesRepository;
import com.rayco.domain.repository.UniversityRepository;
import com.rayco.presentation.dto.GetUniversitiesParamsDTO;
import com.rayco.presentation.dto.UniversityAutocompleteDTO;
import com.rayco.presentation.dto.UniversityDTO;
import com.rayco.presentation.mapper.UniversityMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UniversityService {
    private final UniversityRepository repository;
    private final UniversityMapper mapper;
    private final DistanceComponent distanceComponent;
    private final FavoriteUniversitiesRepository favoriteUniversitiesRepository;
    private final UserService userService;

    public Page<UniversityDTO> listAll(GetUniversitiesParamsDTO dto, Pageable pageable) {
        Page<University> page = repository.findAll(pageable);
        List<UniversityDTO> dtos =  mapper.toListDTO(page.getContent());

        if(dto.isNeedCalcDistance())
            buildDistances(dtos, dto.getLatitude(), dto.getLongitude());

        User user = userService.getLoggedUser();
        List<FavoriteUniversity> favoriteUniversities = favoriteUniversitiesRepository.findByUserId(user.getId());

        if (!favoriteUniversities.isEmpty())
            mapFavoriteUniversities(dtos, favoriteUniversities);

        return new PageImpl<>(
                dtos,
                pageable,
                page.getTotalElements()
        );
    }

    public void favorite(Long id) {
        User user = userService.getLoggedUser();
        FavoriteUniversity favoriteUniversity = favoriteUniversitiesRepository.findByIdUserIdAndIdUniversityId(user.getId(), id);

        if(Objects.nonNull(favoriteUniversity)){
            favoriteUniversitiesRepository.delete(favoriteUniversity);
        } else {
            University university = University.builder().id(id).build();
            FavoriteUniversity newFavoriteUniversity = new FavoriteUniversity(user, university);

            favoriteUniversitiesRepository.save(newFavoriteUniversity);
        }
    }

    public List<UniversityDTO> listAllFavorites(){
        User user = userService.getLoggedUser();
        List<University> universities = favoriteUniversitiesRepository.findByUserId(user.getId())
                .stream()
                .map(FavoriteUniversity::getUniversity)
                .toList();
        return  mapper.toListDTO(universities)
                .stream().peek(universityDTO -> universityDTO.setFavorite(true))
                .toList();
    }

    public List<UniversityAutocompleteDTO> listAutocomplete(){
        List<University> universities = repository.findAll();
        return mapper.toListAutocompleteDTO(universities);
    }

    private void buildDistances(List<UniversityDTO> dtos, double latitude, double longitude){
        for(var university : dtos){
            double distance = distanceComponent.calcDistanceInKM(university.getLatitude(),
                    university.getLongitude(), latitude, longitude);

            university.setDistance(distance);
        }
    }

    private void mapFavoriteUniversities(List<UniversityDTO> dtos, List<FavoriteUniversity> favoriteUniversities) {
        Set<Long> favoriteUniversityIds = favoriteUniversities.stream()
                .map(fav -> fav.getUniversity().getId())
                .collect(Collectors.toSet());

        for (UniversityDTO dto : dtos) {
            dto.setFavorite(favoriteUniversityIds.contains(dto.getId()));
        }
    }
}
