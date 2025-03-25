package com.rayco.presentation.controller;

import com.rayco.application.service.UniversityService;
import com.rayco.presentation.dto.UniversityDTO;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/universities")
@AllArgsConstructor
public class UniversityController {
    private final UniversityService service;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public  ResponseEntity<Page<UniversityDTO>> listAll(
            @RequestParam("needCalcDistance") boolean needCalcDistance,
            @RequestParam("latitude") double latitude,
            @RequestParam("longitude") double longitude,
            Pageable pageable){
        return ResponseEntity.ok(service.listAll(needCalcDistance, latitude, longitude, pageable));
    }
}
