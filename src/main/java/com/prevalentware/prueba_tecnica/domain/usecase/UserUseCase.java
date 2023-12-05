package com.prevalentware.prueba_tecnica.domain.usecase;

import com.prevalentware.prueba_tecnica.domain.api.IUserServicePort;
import com.prevalentware.prueba_tecnica.domain.exception.LogNotFoundException;
import com.prevalentware.prueba_tecnica.domain.model.UserModel;
import com.prevalentware.prueba_tecnica.domain.spi.IUserPersistencePort;

import java.util.List;

public class UserUseCase implements IUserServicePort {
    private final IUserPersistencePort userPersistencePort;

    public UserUseCase(IUserPersistencePort userPersistencePort) {
        this.userPersistencePort = userPersistencePort;
    }

    @Override
    public List<UserModel> getAllUsers(Integer pageNumber, Integer pageSize) {
        List<UserModel> userModelList = userPersistencePort.getAllUsers(pageNumber, pageSize);
        if (userModelList.isEmpty()){
            throw new LogNotFoundException("No User Logs Found");
        }
        return userModelList;
    }

    @Override
    public UserModel getUserByEmail(String email) {
        UserModel userModel = userPersistencePort.getUserByEmail(email);
        if (userModel == null){
            throw new LogNotFoundException("No User Log Found By Email");
        }
        return userModel;
    }
}
