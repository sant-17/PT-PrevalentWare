package com.prevalentware.prueba_tecnica.domain.api;

import com.prevalentware.prueba_tecnica.domain.model.RoleModel;

import java.util.List;

public interface IRoleServicePort {
    List<RoleModel> getAllRoles();
}
