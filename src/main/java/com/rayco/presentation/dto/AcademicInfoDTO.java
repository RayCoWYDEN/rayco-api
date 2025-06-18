package com.rayco.presentation.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AcademicInfoDTO {
    private Long id;
    @NotNull
    private CourseDTO course;
    @NotNull
    private int period;
    @NotNull
    private double tuitionFee;
    @NotNull
    private UniversityDTO university;
}
