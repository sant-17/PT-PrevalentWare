package com.prevalentware.prueba_tecnica.application.dto.response;

import com.prevalentware.prueba_tecnica.domain.model.CountryModel;
import com.prevalentware.prueba_tecnica.domain.model.RoleModel;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
public class UserResponseDto {
    private String id;
    private String email;
    private LocalDateTime emailVerified;
    private LocalDateTime termsAndConditionsAccepted;
    private String name;
    private String image;
    private String position;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private RoleResponseDto roleId;
    private Set<CountryResponseDto> countries;
}
