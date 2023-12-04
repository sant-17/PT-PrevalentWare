package com.prevalentware.prueba_tecnica.application.service.impl;

import com.prevalentware.prueba_tecnica.application.dto.response.SessionResponseDto;
import com.prevalentware.prueba_tecnica.application.mapper.response.ISessionResponseMapper;
import com.prevalentware.prueba_tecnica.application.service.ISessionService;
import com.prevalentware.prueba_tecnica.domain.api.ISessionServicePort;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Transactional
public class SessionService implements ISessionService {
    private final ISessionServicePort sessionServicePort;
    private final ISessionResponseMapper sessionResponseMapper;

    @Override
    public SessionResponseDto getSessionByToken(String token) {
        return sessionResponseMapper.toResponse(sessionServicePort.findSessionByToken(token));
    }
}
