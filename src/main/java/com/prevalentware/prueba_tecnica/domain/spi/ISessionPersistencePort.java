package com.prevalentware.prueba_tecnica.domain.spi;

import com.prevalentware.prueba_tecnica.domain.model.SessionModel;

public interface ISessionPersistencePort {
    SessionModel findSessionByToken(String token);
}
