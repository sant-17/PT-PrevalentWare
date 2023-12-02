package com.prevalentware.prueba_tecnica.application.service.impl;

import com.prevalentware.prueba_tecnica.application.dto.response.CountryResponseDto;
import com.prevalentware.prueba_tecnica.application.service.ICountryService;
import com.prevalentware.prueba_tecnica.application.mapper.response.ICountryResponseMapper;
import com.prevalentware.prueba_tecnica.domain.api.ICountryServicePort;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class CountryService implements ICountryService {

    private final ICountryServicePort countryServicePort;
    private final ICountryResponseMapper countryResponseMapper;

    @Override
    public List<CountryResponseDto> getAllCountries(Integer pageNumber, Integer pageSize) {
        return countryResponseMapper.toResponseList(countryServicePort.getAllCountries(pageNumber, pageSize));
    }
}