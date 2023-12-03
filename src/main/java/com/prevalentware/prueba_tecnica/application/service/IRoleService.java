package com.prevalentware.prueba_tecnica.application.service;

import com.prevalentware.prueba_tecnica.application.dto.response.RoleResponseDto;

import java.util.List;

public interface IRoleService {
    List<RoleResponseDto> getAllRoles();
}
