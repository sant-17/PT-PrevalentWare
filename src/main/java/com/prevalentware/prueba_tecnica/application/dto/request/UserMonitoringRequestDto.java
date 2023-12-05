package com.prevalentware.prueba_tecnica.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UserMonitoringRequestDto {
    @NotBlank(message = "Field 'email' it's required")
    private String email;
    @NotNull(message = "Field 'from' it's required")
    private LocalDate from;
    @NotNull(message = "Field 'to' it's required")
    private LocalDate to;
}
