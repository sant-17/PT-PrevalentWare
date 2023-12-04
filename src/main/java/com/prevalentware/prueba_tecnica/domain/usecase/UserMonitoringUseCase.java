package com.prevalentware.prueba_tecnica.domain.usecase;

import com.prevalentware.prueba_tecnica.domain.api.IUserMonitoringServicePort;
import com.prevalentware.prueba_tecnica.domain.model.UserModel;
import com.prevalentware.prueba_tecnica.domain.model.UserMonitoringModel;
import com.prevalentware.prueba_tecnica.domain.spi.IUserMonitoringPersistencePort;
import com.prevalentware.prueba_tecnica.domain.spi.IUserPersistencePort;

import java.time.LocalDate;
import java.util.List;

public class UserMonitoringUseCase implements IUserMonitoringServicePort {
    private final IUserMonitoringPersistencePort userMonitoringPersistencePort;
    private final IUserPersistencePort userPersistencePort;

    public UserMonitoringUseCase(IUserMonitoringPersistencePort userMonitoringPersistencePort, IUserPersistencePort userPersistencePort) {
        this.userMonitoringPersistencePort = userMonitoringPersistencePort;
        this.userPersistencePort = userPersistencePort;
    }

    @Override
    public List<UserMonitoringModel> getUserMonitoringByEmail(String email, LocalDate from, LocalDate to) {
        UserModel userModel = userPersistencePort.getUserByEmail(email);
        return userMonitoringPersistencePort.getUserMonitoringByEmail(userModel, from, to);
    }
}
