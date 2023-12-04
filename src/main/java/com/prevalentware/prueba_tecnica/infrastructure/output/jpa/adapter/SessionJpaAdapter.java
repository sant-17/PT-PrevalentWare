package com.prevalentware.prueba_tecnica.infrastructure.output.jpa.adapter;

import com.prevalentware.prueba_tecnica.domain.model.SessionModel;
import com.prevalentware.prueba_tecnica.domain.spi.ISessionPersistencePort;
import com.prevalentware.prueba_tecnica.infrastructure.output.jpa.entity.Session;
import com.prevalentware.prueba_tecnica.infrastructure.output.jpa.mapper.ISessionMapper;
import com.prevalentware.prueba_tecnica.infrastructure.output.jpa.repository.ISessionRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SessionJpaAdapter implements ISessionPersistencePort {
    private final ISessionRepository sessionRepository;
    private final ISessionMapper sessionMapper;

    @Override
    public SessionModel findSessionByToken(String token) {
        Session session = sessionRepository.findBySessionToken(token);
        return sessionMapper.toSessionModel(session);
    }
}
