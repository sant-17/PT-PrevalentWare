package com.prevalentware.prueba_tecnica.infrastructure.output.jpa.adapter;

import com.prevalentware.prueba_tecnica.domain.model.CountryModel;
import com.prevalentware.prueba_tecnica.domain.spi.ICountryPersistencePort;
import com.prevalentware.prueba_tecnica.infrastructure.output.jpa.entity.Country;
import com.prevalentware.prueba_tecnica.infrastructure.output.jpa.mapper.ICountryMapper;
import com.prevalentware.prueba_tecnica.infrastructure.output.jpa.repository.ICountryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class CountryJpaAdapter implements ICountryPersistencePort {

    private final ICountryRepository countryRepository;
    private final ICountryMapper countryMapper;

    @Override
    public List<CountryModel> getAllCountries(Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return countryRepository.findAll(pageable)
                .stream()
                .map(countryMapper::toCountryModel)
                .collect(Collectors.toList());
    }
}
