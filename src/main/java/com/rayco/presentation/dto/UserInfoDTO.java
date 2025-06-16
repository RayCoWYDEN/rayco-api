package com.rayco.presentation.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserInfoDTO {
    private String name;
    private String email;
    private Long courseId;
    private Long universityId;
    private int period;
    private String tuitionFee;
}
