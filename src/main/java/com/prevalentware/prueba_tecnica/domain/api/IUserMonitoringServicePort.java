package com.prevalentware.prueba_tecnica.domain.api;

import com.prevalentware.prueba_tecnica.domain.model.UserMonitoringModel;

import java.time.LocalDate;
import java.util.List;

public interface IUserMonitoringServicePort {
    List<UserMonitoringModel> getUserMonitoringByEmail(String email, LocalDate from, LocalDate to);
}
