package com.prevalentware.prueba_tecnica.application.service;

import com.prevalentware.prueba_tecnica.application.dto.response.UserResponseDto;

import java.util.List;

public interface IUserService {
    List<UserResponseDto> getAllUsers(Integer pageNumber, Integer pageSize);
    UserResponseDto getUserByEmail(String email);
}
