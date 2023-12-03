package com.prevalentware.prueba_tecnica.application.dto.response;

import com.prevalentware.prueba_tecnica.domain.utils.RoleName;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class RoleResponseDto {
    private String id;
    private RoleName name;
    private LocalDateTime createdAt;
}
