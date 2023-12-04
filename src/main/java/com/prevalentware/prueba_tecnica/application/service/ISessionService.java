package com.prevalentware.prueba_tecnica.application.service;

import com.prevalentware.prueba_tecnica.application.dto.response.SessionResponseDto;

public interface ISessionService {
    SessionResponseDto getSessionByToken(String token);
}
