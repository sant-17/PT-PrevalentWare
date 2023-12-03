package com.prevalentware.prueba_tecnica.infrastructure.output.jpa.repository;

import com.prevalentware.prueba_tecnica.infrastructure.output.jpa.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRoleRepository extends JpaRepository<Role, String> {
}
