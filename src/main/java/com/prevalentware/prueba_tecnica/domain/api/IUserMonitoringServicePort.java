package com.prevalentware.prueba_tecnica.domain.api;

import com.prevalentware.prueba_tecnica.domain.model.UserMonitoringModel;

import java.time.LocalDate;

public interface IUserMonitoringServicePort {
    UserMonitoringModel getUserMonitoringByEmailBetweenDates(String email, LocalDate fromDate, LocalDate toDate);
}
