package com.prevalentware.prueba_tecnica.domain.spi;

import com.prevalentware.prueba_tecnica.domain.model.UserModel;

import java.util.List;

public interface IUserPersistencePort {
    List<UserModel> getAllUsers(Integer pageNumber, Integer pageSize);
    UserModel getUserByEmail(String email);
}
