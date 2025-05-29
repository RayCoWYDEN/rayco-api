package com.rayco.domain.repository;

import com.rayco.domain.entity.FavoriteUniversity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavoriteUniversitiesRepository extends JpaRepository<FavoriteUniversity, Long> {

    List<FavoriteUniversity> findByUserId(Long id);

    FavoriteUniversity findByIdUserIdAndIdUniversityId(Long userId, Long universityId);
}
