package com.prevalentware.prueba_tecnica.application.service.impl;

import com.prevalentware.prueba_tecnica.application.dto.response.UserResponseDto;
import com.prevalentware.prueba_tecnica.application.mapper.response.IUserResponseMapper;
import com.prevalentware.prueba_tecnica.application.service.IUserService;
import com.prevalentware.prueba_tecnica.domain.api.IUserServicePort;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class UserService implements IUserService {
    private final IUserServicePort servicePort;
    private final IUserResponseMapper userResponseMapper;

    @Override
    public List<UserResponseDto> getAllUsers(Integer pageNumber, Integer pageSize) {
        return userResponseMapper.toResponseList(servicePort.getAllUsers(pageNumber, pageSize));
    }

    @Override
    public UserResponseDto getUserByEmail(String email) {
        return userResponseMapper.toResponse(servicePort.getUserByEmail(email));
    }
}
