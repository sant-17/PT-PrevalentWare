package com.prevalentware.prueba_tecnica.application.service.impl;

import com.prevalentware.prueba_tecnica.application.dto.response.UserMonitoringResponseDto;
import com.prevalentware.prueba_tecnica.application.mapper.response.IUserMonitoringResponseMapper;
import com.prevalentware.prueba_tecnica.application.service.IUserMonitoringService;
import com.prevalentware.prueba_tecnica.domain.api.IUserMonitoringServicePort;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class UserMonitoringService implements IUserMonitoringService {
    private final IUserMonitoringServicePort userMonitoringServicePort;
    private final IUserMonitoringResponseMapper userMonitoringResponseMapper;

    @Override
    public List<UserMonitoringResponseDto> getUserMonitoringByEmail(String email, LocalDate from, LocalDate to) {
        return userMonitoringResponseMapper.toResponseList(userMonitoringServicePort.getUserMonitoringByEmail(email, from, to));
    }
}
