package com.prevalentware.prueba_tecnica.infrastructure.output.jpa.adapter;

import com.prevalentware.prueba_tecnica.domain.model.RoleModel;
import com.prevalentware.prueba_tecnica.domain.spi.IRolePersistencePort;
import com.prevalentware.prueba_tecnica.infrastructure.output.jpa.entity.Role;
import com.prevalentware.prueba_tecnica.infrastructure.output.jpa.mapper.IRoleMapper;
import com.prevalentware.prueba_tecnica.infrastructure.output.jpa.repository.IRoleRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class RoleJpaAdapter implements IRolePersistencePort {
    private final IRoleRepository roleRepository;
    private final IRoleMapper roleMapper;

    @Override
    public List<RoleModel> getAllRoles() {
        List<Role> roleList = roleRepository.findAll();
        return roleMapper.toRoleModelList(roleList);
    }
}
