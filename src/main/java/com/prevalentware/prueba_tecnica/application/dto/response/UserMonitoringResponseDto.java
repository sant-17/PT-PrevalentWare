package com.prevalentware.prueba_tecnica.application.dto.response;

import com.prevalentware.prueba_tecnica.domain.model.UserModel;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UserMonitoringResponseDto {
    private String id;
    private Integer usage;
    private String description;
    private String userId;
    private LocalDateTime createdAt;
}
