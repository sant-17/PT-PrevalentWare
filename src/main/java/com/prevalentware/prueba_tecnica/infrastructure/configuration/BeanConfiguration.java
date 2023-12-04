package com.prevalentware.prueba_tecnica.infrastructure.configuration;

import com.prevalentware.prueba_tecnica.domain.api.ICountryServicePort;
import com.prevalentware.prueba_tecnica.domain.api.IRoleServicePort;
import com.prevalentware.prueba_tecnica.domain.api.IUserMonitoringServicePort;
import com.prevalentware.prueba_tecnica.domain.api.IUserServicePort;
import com.prevalentware.prueba_tecnica.domain.spi.ICountryPersistencePort;
import com.prevalentware.prueba_tecnica.domain.spi.IRolePersistencePort;
import com.prevalentware.prueba_tecnica.domain.spi.IUserMonitoringPersistencePort;
import com.prevalentware.prueba_tecnica.domain.spi.IUserPersistencePort;
import com.prevalentware.prueba_tecnica.domain.usecase.CountryUseCase;
import com.prevalentware.prueba_tecnica.domain.usecase.RoleUseCase;
import com.prevalentware.prueba_tecnica.domain.usecase.UserMonitoringUseCase;
import com.prevalentware.prueba_tecnica.domain.usecase.UserUseCase;
import com.prevalentware.prueba_tecnica.infrastructure.output.jpa.adapter.CountryJpaAdapter;
import com.prevalentware.prueba_tecnica.infrastructure.output.jpa.adapter.RoleJpaAdapter;
import com.prevalentware.prueba_tecnica.infrastructure.output.jpa.adapter.UserJpaAdapter;
import com.prevalentware.prueba_tecnica.infrastructure.output.jpa.adapter.UserMonitoringJpaAdapter;
import com.prevalentware.prueba_tecnica.infrastructure.output.jpa.mapper.ICountryMapper;
import com.prevalentware.prueba_tecnica.infrastructure.output.jpa.mapper.IRoleMapper;
import com.prevalentware.prueba_tecnica.infrastructure.output.jpa.mapper.IUserMapper;
import com.prevalentware.prueba_tecnica.infrastructure.output.jpa.mapper.IUserMonitoringMapper;
import com.prevalentware.prueba_tecnica.infrastructure.output.jpa.repository.ICountryRepository;
import com.prevalentware.prueba_tecnica.infrastructure.output.jpa.repository.IRoleRepository;
import com.prevalentware.prueba_tecnica.infrastructure.output.jpa.repository.IUserMonitoringRepository;
import com.prevalentware.prueba_tecnica.infrastructure.output.jpa.repository.IUserRepository;
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
}
