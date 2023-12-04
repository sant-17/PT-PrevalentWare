package com.prevalentware.prueba_tecnica.infrastructure.output.jpa.repository;

import com.prevalentware.prueba_tecnica.infrastructure.output.jpa.entity.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.util.Optional;

public interface ISessionRepository extends JpaRepository<Session, String> {
    Session findBySessionToken(@NonNull String sessionToken);
}
