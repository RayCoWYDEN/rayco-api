package com.rayco.presentation.controller;

import com.rayco.application.service.UniversityService;
import com.rayco.presentation.dto.GetUniversitiesParamsDTO;
import com.rayco.presentation.dto.UniversityAutocompleteDTO;
import com.rayco.presentation.dto.UniversityDTO;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/universities")
@AllArgsConstructor
public class UniversityController {
    private final UniversityService service;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Page<UniversityDTO>> listAll(
            @ModelAttribute GetUniversitiesParamsDTO paramsDTO,
            Pageable pageable){
        return ResponseEntity.ok(service.listAll(paramsDTO, pageable));
    }

    @GetMapping("/favorites")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<UniversityDTO>> listFavorites(){
        return ResponseEntity.ok(service.listAllFavorites());
    }

    @PatchMapping("/favorite/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> markAsFavorite(@PathVariable("id") Long id){
        service.favorite(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/autocomplete")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<UniversityAutocompleteDTO>> autocomplete(){
        return ResponseEntity.ok(service.listAutocomplete());
    }
}
