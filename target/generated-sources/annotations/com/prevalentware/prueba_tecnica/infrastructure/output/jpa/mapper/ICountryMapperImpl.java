package com.prevalentware.prueba_tecnica.infrastructure.output.jpa.mapper;

import com.prevalentware.prueba_tecnica.domain.model.CountryModel;
import com.prevalentware.prueba_tecnica.infrastructure.output.jpa.entity.Country;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-12-05T01:36:21-0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.9 (Amazon.com Inc.)"
)
@Component
public class ICountryMapperImpl implements ICountryMapper {

    @Override
    public CountryModel toCountryModel(Country country) {
        if ( country == null ) {
            return null;
        }

        CountryModel countryModel = new CountryModel();

        countryModel.setId( country.getId() );
        countryModel.setName( country.getName() );
        countryModel.setCreatedAt( country.getCreatedAt() );
        countryModel.setUpdatedAt( country.getUpdatedAt() );

        return countryModel;
    }
}
