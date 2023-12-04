package com.prevalentware.prueba_tecnica.domain.api;

import com.prevalentware.prueba_tecnica.domain.model.SessionModel;

public interface ISessionServicePort {
    SessionModel findSessionByToken(String token);
}
