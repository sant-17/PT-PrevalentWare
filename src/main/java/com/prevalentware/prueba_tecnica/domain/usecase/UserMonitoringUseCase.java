package com.prevalentware.prueba_tecnica.domain.usecase;

import com.prevalentware.prueba_tecnica.domain.api.IUserMonitoringServicePort;
import com.prevalentware.prueba_tecnica.domain.model.UserMonitoringModel;
import com.prevalentware.prueba_tecnica.domain.spi.IUserMonitoringPersistencePort;

import java.time.LocalDate;

public class UserMonitoringUseCase implements IUserMonitoringServicePort {
    private final IUserMonitoringPersistencePort userMonitoringPersistencePort;

    public UserMonitoringUseCase(IUserMonitoringPersistencePort userMonitoringPersistencePort) {
        this.userMonitoringPersistencePort = userMonitoringPersistencePort;
    }

    @Override
    public UserMonitoringModel getUserMonitoringByEmailBetweenDates(String email, LocalDate fromDate, LocalDate toDate) {
        return userMonitoringPersistencePort.getUserMonitoringByEmailBetweenDates(email, fromDate, toDate);
    }
}
