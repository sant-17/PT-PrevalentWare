package com.prevalentware.prueba_tecnica.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class TopUsersMonitoringRequestDto {
    LocalDate from;
    LocalDate to;
}
