package com.prevalentware.prueba_tecnica.domain.spi;

import com.prevalentware.prueba_tecnica.domain.model.RoleModel;

import java.util.List;

public interface IRolePersistencePort {
    List<RoleModel> getAllRoles();
}
