package com.prevalentware.prueba_tecnica.domain.usecase;

import com.prevalentware.prueba_tecnica.domain.api.ISessionServicePort;
import com.prevalentware.prueba_tecnica.domain.model.SessionModel;
import com.prevalentware.prueba_tecnica.domain.spi.ISessionPersistencePort;

public class SessionUseCase implements ISessionServicePort {
    private final ISessionPersistencePort sessionPersistencePort;

    public SessionUseCase(ISessionPersistencePort sessionPersistencePort) {
        this.sessionPersistencePort = sessionPersistencePort;
    }

    @Override
    public SessionModel findSessionByToken(String token) {
        return sessionPersistencePort.findSessionByToken(token);
    }
}
