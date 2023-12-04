package com.prevalentware.prueba_tecnica.infrastructure.configuration;

import com.prevalentware.prueba_tecnica.domain.api.*;
import com.prevalentware.prueba_tecnica.domain.spi.*;
import com.prevalentware.prueba_tecnica.domain.usecase.*;
import com.prevalentware.prueba_tecnica.infrastructure.output.jpa.adapter.*;
import com.prevalentware.prueba_tecnica.infrastructure.output.jpa.mapper.*;
import com.prevalentware.prueba_tecnica.infrastructure.output.jpa.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
    private final ICountryRepository countryRepository;
    private final ICountryMapper countryMapper;
    private final IRoleRepository roleRepository;
    private final IRoleMapper roleMapper;
    private final IUserRepository userRepository;
    private final IUserMapper userMapper;
    private final IUserMonitoringRepository userMonitoringRepository;
    private final IUserMonitoringMapper userMonitoringMapper;
    private final ISessionRepository sessionRepository;
    private final ISessionMapper sessionMapper;

    @Bean
    public ICountryPersistencePort countryPersistencePort(){
        return new CountryJpaAdapter(countryRepository, countryMapper);
    }

    @Bean
    public ICountryServicePort countryServicePort(){
        return new CountryUseCase(countryPersistencePort());
    }

    @Bean
    public IRolePersistencePort rolePersistencePort(){
        return new RoleJpaAdapter(roleRepository, roleMapper);
    }

    @Bean
    public IRoleServicePort roleServicePort(){
        return new RoleUseCase(rolePersistencePort());
    }

    @Bean
    public IUserPersistencePort userPersistencePort(){
        return new UserJpaAdapter(userRepository, userMapper);
    }

    @Bean
    public IUserServicePort userServicePort(){
        return new UserUseCase(userPersistencePort());
    }

    @Bean
    public IUserMonitoringPersistencePort userMonitoringPersistencePort(){
        return new UserMonitoringJpaAdapter(userMonitoringRepository, userMonitoringMapper, userMapper);
    }

    @Bean
    public IUserMonitoringServicePort userMonitoringServicePort(){
        return new UserMonitoringUseCase(userMonitoringPersistencePort(), userPersistencePort());
    }

    @Bean
    public ISessionPersistencePort sessionPersistencePort(){
        return new SessionJpaAdapter(sessionRepository, sessionMapper);
    }

    @Bean
    public ISessionServicePort sessionServicePort(){
        return new SessionUseCase(sessionPersistencePort());
    }
}
