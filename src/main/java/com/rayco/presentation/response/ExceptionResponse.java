package com.rayco.presentation.response;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@Builder
public class ExceptionResponse {
    private LocalDateTime timestamp;
    private HttpStatus status;
    private String error;
    private String message;
}
