package com.prevalentware.prueba_tecnica.domain.api;

import com.prevalentware.prueba_tecnica.domain.model.UserModel;

import java.util.List;

public interface IUserServicePort {
    List<UserModel> getAllUsers(Integer pageNumber, Integer pageSize);
    UserModel getUserByEmail(String email);
}
