package com.prevalentware.prueba_tecnica.domain.api;

import com.prevalentware.prueba_tecnica.domain.model.CountryModel;

import java.util.List;

public interface ICountryServicePort {
    List<CountryModel> getAllCountries(Integer pageNumber, Integer pageSize);
}
