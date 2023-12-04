package com.prevalentware.prueba_tecnica.infrastructure.output.jpa.adapter;

import com.prevalentware.prueba_tecnica.domain.model.UserModel;
import com.prevalentware.prueba_tecnica.domain.model.UserMonitoringModel;
import com.prevalentware.prueba_tecnica.domain.spi.IUserMonitoringPersistencePort;
import com.prevalentware.prueba_tecnica.infrastructure.output.jpa.entity.User;
import com.prevalentware.prueba_tecnica.infrastructure.output.jpa.mapper.IUserMapper;
import com.prevalentware.prueba_tecnica.infrastructure.output.jpa.mapper.IUserMonitoringMapper;
import com.prevalentware.prueba_tecnica.infrastructure.output.jpa.repository.IUserMonitoringRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class UserMonitoringJpaAdapter implements IUserMonitoringPersistencePort {
    private final IUserMonitoringRepository userMonitoringRepository;
    private final IUserMonitoringMapper userMonitoringMapper;
    private final IUserMapper userMapper;

    @Override
    public List<UserMonitoringModel> getUserMonitoringByEmail(UserModel userModel, LocalDate from, LocalDate to) {
        User user = userMapper.toUser(userModel);
        LocalDateTime fromDateTime = from.atTime(0, 0);
        LocalDateTime toDateTime = to.atTime(0, 0);
        return userMonitoringRepository.findUserMonitoringByUserIdBetweenDates(user, fromDateTime, toDateTime)
                .stream()
                .map(userMonitoringMapper::toUserMonitoringModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserModel> getTopUsersByMonitoring(LocalDate from, LocalDate to) {
        Pageable pageable = PageRequest.of(0, 3);
        LocalDateTime fromDateTime = from.atTime(0, 0);
        LocalDateTime toDateTime = to.atTime(0, 0);
        return userMonitoringRepository.findTopUsersByMonitoring(fromDateTime, toDateTime, pageable)
                .stream()
                .map(userMapper::toUserModel)
                .collect(Collectors.toList());
    }
}
