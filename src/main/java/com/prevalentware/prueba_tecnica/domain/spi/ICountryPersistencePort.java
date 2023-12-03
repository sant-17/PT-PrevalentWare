package com.prevalentware.prueba_tecnica.domain.spi;

import com.prevalentware.prueba_tecnica.domain.model.CountryModel;

import java.util.List;

public interface ICountryPersistencePort {
    List<CountryModel> getAllCountries(Integer pageNumber, Integer pageSize);
}
