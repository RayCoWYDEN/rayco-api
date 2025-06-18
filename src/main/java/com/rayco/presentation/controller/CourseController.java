package com.rayco.presentation.controller;

import com.rayco.application.service.CourseService;
import com.rayco.presentation.dto.CourseAutocompleteDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/courses")
@AllArgsConstructor
public class CourseController {
    private final CourseService service;
    @GetMapping("/autocomplete")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<CourseAutocompleteDTO>> autocomplete(){
        return ResponseEntity.ok(service.listAutocomplete());
    }
}
