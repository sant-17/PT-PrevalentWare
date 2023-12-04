package com.prevalentware.prueba_tecnica.application.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class TopUsersResponseDto {
    private String id;
    private String email;
    private String name;
    private LocalDateTime createdAt;
    private RoleResponseDto roleId;
}
