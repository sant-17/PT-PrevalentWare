package com.prevalentware.prueba_tecnica.domain.spi;

import com.prevalentware.prueba_tecnica.domain.model.UserMonitoringModel;

import java.time.LocalDate;

public interface IUserMonitoringPersistencePort {
    UserMonitoringModel getUserMonitoringByEmailBetweenDates(String email, LocalDate fromDate, LocalDate toDate);
}
