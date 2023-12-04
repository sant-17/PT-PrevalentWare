package com.prevalentware.prueba_tecnica.application.dto.response;

import com.prevalentware.prueba_tecnica.domain.model.UserModel;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class SessionResponseDto {
    private String id;
    private String sessionToken;
    private UserResponseDto userId;
    private LocalDateTime expiresAt;
    private LocalDateTime createdAt;
}
