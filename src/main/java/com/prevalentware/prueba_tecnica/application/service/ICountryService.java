package com.prevalentware.prueba_tecnica.application.service;

import com.prevalentware.prueba_tecnica.application.dto.response.CountryResponseDto;

import java.util.List;

public interface ICountryService {
    List<CountryResponseDto> getAllCountries(Integer pageNumber, Integer pageSize);
}