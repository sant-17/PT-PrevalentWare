package com.prevalentware.prueba_tecnica.infrastructure.configuration;

import com.prevalentware.prueba_tecnica.domain.api.ICountryServicePort;
import com.prevalentware.prueba_tecnica.domain.api.IRoleServicePort;
import com.prevalentware.prueba_tecnica.domain.spi.ICountryPersistencePort;
import com.prevalentware.prueba_tecnica.domain.spi.IRolePersistencePort;
import com.prevalentware.prueba_tecnica.domain.usecase.CountryUseCase;
import com.prevalentware.prueba_tecnica.domain.usecase.RoleUseCase;
import com.prevalentware.prueba_tecnica.infrastructure.output.jpa.adapter.CountryJpaAdapter;
import com.prevalentware.prueba_tecnica.infrastructure.output.jpa.adapter.RoleJpaAdapter;
import com.prevalentware.prueba_tecnica.infrastructure.output.jpa.mapper.ICountryMapper;
import com.prevalentware.prueba_tecnica.infrastructure.output.jpa.mapper.IRoleMapper;
import com.prevalentware.prueba_tecnica.infrastructure.output.jpa.repository.ICountryRepository;
import com.prevalentware.prueba_tecnica.infrastructure.output.jpa.repository.IRoleRepository;
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
}
