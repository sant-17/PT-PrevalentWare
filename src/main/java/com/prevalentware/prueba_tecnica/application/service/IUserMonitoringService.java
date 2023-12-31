package com.prevalentware.prueba_tecnica.application.service;

import com.prevalentware.prueba_tecnica.application.dto.response.TopUsersResponseDto;
import com.prevalentware.prueba_tecnica.application.dto.response.UserMonitoringResponseDto;
import com.prevalentware.prueba_tecnica.application.dto.response.UserResponseDto;
import com.prevalentware.prueba_tecnica.domain.model.UserModel;
import com.prevalentware.prueba_tecnica.domain.model.UserMonitoringModel;

import java.time.LocalDate;
import java.util.List;

public interface IUserMonitoringService {
    List<UserMonitoringResponseDto> getUserMonitoringByEmail(String email, LocalDate from, LocalDate to);
    List<TopUsersResponseDto> getTopUsersByMonitoring(LocalDate from, LocalDate to);
}
