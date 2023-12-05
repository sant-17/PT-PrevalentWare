package com.prevalentware.prueba_tecnica.domain.usecase;

import com.prevalentware.prueba_tecnica.domain.api.ICountryServicePort;
import com.prevalentware.prueba_tecnica.domain.exception.LogNotFoundException;
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
        List<CountryModel> countryModelList = countryPersistencePort.getAllCountries(pageNumber, pageSize);
        if (countryModelList.isEmpty()){
            throw new LogNotFoundException("No Country Logs Found");
        }
        return countryModelList;
    }
}
