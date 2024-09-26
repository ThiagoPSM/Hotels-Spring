package com.thiago.cities_service.exception.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
@Data
@Builder
@AllArgsConstructor @NoArgsConstructor
public class ErrorDTO {
    private HttpStatus status;
    private String message;
}
