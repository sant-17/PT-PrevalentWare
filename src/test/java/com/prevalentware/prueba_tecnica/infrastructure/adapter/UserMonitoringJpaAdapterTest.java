package com.prevalentware.prueba_tecnica.infrastructure.adapter;

import com.prevalentware.prueba_tecnica.domain.model.UserModel;
import com.prevalentware.prueba_tecnica.domain.model.UserMonitoringModel;
import com.prevalentware.prueba_tecnica.infrastructure.output.jpa.adapter.UserMonitoringJpaAdapter;
import com.prevalentware.prueba_tecnica.infrastructure.output.jpa.entity.User;
import com.prevalentware.prueba_tecnica.infrastructure.output.jpa.entity.UserMonitoring;
import com.prevalentware.prueba_tecnica.infrastructure.output.jpa.mapper.IUserMapper;
import com.prevalentware.prueba_tecnica.infrastructure.output.jpa.mapper.IUserMonitoringMapper;
import com.prevalentware.prueba_tecnica.infrastructure.output.jpa.repository.IUserMonitoringRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UserMonitoringJpaAdapterTest {

    @Mock
    private IUserMonitoringRepository userMonitoringRepository;

    @Mock
    private IUserMonitoringMapper userMonitoringMapper;

    @Mock
    private IUserMapper userMapper;

    private UserMonitoringJpaAdapter userMonitoringJpaAdapter;

    @BeforeEach
    public void setUp() {
        userMonitoringJpaAdapter = new UserMonitoringJpaAdapter(userMonitoringRepository, userMonitoringMapper, userMapper);
    }

    @Test
    public void testGetTopUsersByMonitoring() {
        LocalDate from = LocalDate.now();
        LocalDate to = LocalDate.now();
        User user = new User();
        UserModel userModel = new UserModel();
        List<User> users = Collections.singletonList(user);

        when(userMonitoringRepository.findTopUsersByMonitoring(any(LocalDateTime.class), any(LocalDateTime.class), any(Pageable.class))).thenReturn(users);
        when(userMapper.toUserModel(user)).thenReturn(userModel);

        //Llamada al método del test getTopUsersByMonitoring
        List<UserModel> result = userMonitoringJpaAdapter.getTopUsersByMonitoring(from, to);

        assertEquals(1, result.size());
        assertEquals(userModel, result.get(0));

        verify(userMonitoringRepository).findTopUsersByMonitoring(from.atTime(0, 0), to.atTime(0, 0), PageRequest.of(0, 3));
        verify(userMapper).toUserModel(user);
    }

    @Test
    public void testGetUserMonitoringByEmail() {
        LocalDate from = LocalDate.now();
        LocalDate to = LocalDate.now();
        User user = new User();
        UserModel userModel = new UserModel();
        UserMonitoring userMonitoring = new UserMonitoring();
        UserMonitoringModel userMonitoringModel = new UserMonitoringModel();
        List<UserMonitoring> userMonitorings = Collections.singletonList(userMonitoring);

        when(userMapper.toUser(userModel)).thenReturn(user);
        when(userMonitoringRepository.findUserMonitoringByUserIdBetweenDates(user, from.atTime(0, 0), to.atTime(0, 0))).thenReturn(userMonitorings);
        when(userMonitoringMapper.toUserMonitoringModel(userMonitoring)).thenReturn(userMonitoringModel);

        //Llamada al método del test getUserMonitoringByEmail
        List<UserMonitoringModel> result = userMonitoringJpaAdapter.getUserMonitoringByEmail(userModel, from, to);

        assertEquals(1, result.size());
        assertEquals(userMonitoringModel, result.get(0));

        verify(userMapper).toUser(userModel);
        verify(userMonitoringRepository).findUserMonitoringByUserIdBetweenDates(user, from.atTime(0, 0), to.atTime(0, 0));
        verify(userMonitoringMapper).toUserMonitoringModel(userMonitoring);
    }
}
