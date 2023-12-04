package com.prevalentware.prueba_tecnica.infrastructure.output.jpa.mapper;

import com.prevalentware.prueba_tecnica.domain.model.CountryModel;
import com.prevalentware.prueba_tecnica.infrastructure.output.jpa.entity.Country;
import java.time.LocalDateTime;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-12-04T16:11:23-0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.9 (Amazon.com Inc.)"
)
@Component
public class ICountryMapperImpl implements ICountryMapper {

    @Override
    public CountryModel toCountryModel(Country country) {
        if ( country == null ) {
            return null;
        }

        String id = null;
        String name = null;
        LocalDateTime createdAt = null;
        LocalDateTime updatedAt = null;

        id = country.getId();
        name = country.getName();
        createdAt = country.getCreatedAt();
        updatedAt = country.getUpdatedAt();

        CountryModel countryModel = new CountryModel( id, name, createdAt, updatedAt );

        return countryModel;
    }
}
