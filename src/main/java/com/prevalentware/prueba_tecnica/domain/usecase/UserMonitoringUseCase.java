package com.prevalentware.prueba_tecnica.domain.usecase;

import com.prevalentware.prueba_tecnica.domain.api.IUserMonitoringServicePort;
import com.prevalentware.prueba_tecnica.domain.exception.DateOrderViolationException;
import com.prevalentware.prueba_tecnica.domain.exception.LogNotFoundException;
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
        if (userModel == null){
            throw new LogNotFoundException("No User Log Found By Email");
        }

        if (to.isBefore(from)){
            throw new DateOrderViolationException();
        }

        List<UserMonitoringModel> userMonitoringModelList = userMonitoringPersistencePort.getUserMonitoringByEmail(userModel, from, to);
        if (userMonitoringModelList.isEmpty()){
            throw new LogNotFoundException("No UserMonitoring Logs Found");
        }

        return userMonitoringModelList;
    }

    @Override
    public List<UserModel> getTopUsersByMonitoring(LocalDate from, LocalDate to) {
        if (to.isBefore(from)){
            throw new DateOrderViolationException();
        }

        List<UserModel> userModelList = userMonitoringPersistencePort.getTopUsersByMonitoring(from, to);
        if (userModelList.isEmpty()){
            throw new LogNotFoundException("No User Monitoring Found");
        }

        return userMonitoringPersistencePort.getTopUsersByMonitoring(from, to);
    }
}
