package com.prevalentware.prueba_tecnica.domain.spi;

import com.prevalentware.prueba_tecnica.domain.model.UserModel;
import com.prevalentware.prueba_tecnica.domain.model.UserMonitoringModel;

import java.time.LocalDate;
import java.util.List;

public interface IUserMonitoringPersistencePort {
    List<UserMonitoringModel> getUserMonitoringByEmail(UserModel userModel, LocalDate from, LocalDate to);
}
