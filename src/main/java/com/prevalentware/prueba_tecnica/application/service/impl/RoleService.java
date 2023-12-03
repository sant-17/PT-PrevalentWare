package com.prevalentware.prueba_tecnica.application.service.impl;

import com.prevalentware.prueba_tecnica.application.dto.response.RoleResponseDto;
import com.prevalentware.prueba_tecnica.application.mapper.response.IRoleResponseMapper;
import com.prevalentware.prueba_tecnica.application.service.IRoleService;
import com.prevalentware.prueba_tecnica.domain.api.IRoleServicePort;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class RoleService implements IRoleService {
    private final IRoleServicePort roleServicePort;
    private final IRoleResponseMapper roleResponseMapper;

    @Override
    public List<RoleResponseDto> getAllRoles() {
        return roleResponseMapper.toResponseList(roleServicePort.getAllRoles());
    }
}
