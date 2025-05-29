package com.rayco.domain.repository;

import com.rayco.domain.entity.University;
import com.rayco.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UniversityRepository extends JpaRepository<University, Long> {
}
