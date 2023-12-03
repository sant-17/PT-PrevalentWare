package com.prevalentware.prueba_tecnica.domain.usecase;

import com.prevalentware.prueba_tecnica.domain.api.ICountryServicePort;
import com.prevalentware.prueba_tecnica.domain.model.CountryModel;
import com.prevalentware.prueba_tecnica.domain.spi.ICountryPersistencePort;

import java.util.List;

public class CountryUseCase implements ICountryServicePort {

    private final ICountryPersistencePort countryPersistencePort;

    public CountryUseCase(ICountryPersistencePort countryPersistencePort) {
        this.countryPersistencePort = countryPersistencePort;
    }

    @Override
    public List<CountryModel> getAllCountries(Integer pageNumber, Integer pageSize) {
        return countryPersistencePort.getAllCountries(pageNumber, pageSize);
    }
}
