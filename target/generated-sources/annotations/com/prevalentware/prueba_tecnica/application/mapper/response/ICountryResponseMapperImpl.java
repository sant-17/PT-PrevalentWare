package com.prevalentware.prueba_tecnica.application.mapper.response;

import com.prevalentware.prueba_tecnica.application.dto.response.CountryResponseDto;
import com.prevalentware.prueba_tecnica.domain.model.CountryModel;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-12-05T18:58:50-0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.9 (Amazon.com Inc.)"
)
@Component
public class ICountryResponseMapperImpl implements ICountryResponseMapper {

    @Override
    public List<CountryResponseDto> toResponseList(List<CountryModel> countryModelList) {
        if ( countryModelList == null ) {
            return null;
        }

        List<CountryResponseDto> list = new ArrayList<CountryResponseDto>( countryModelList.size() );
        for ( CountryModel countryModel : countryModelList ) {
            list.add( countryModelToCountryResponseDto( countryModel ) );
        }

        return list;
    }

    protected CountryResponseDto countryModelToCountryResponseDto(CountryModel countryModel) {
        if ( countryModel == null ) {
            return null;
        }

        CountryResponseDto countryResponseDto = new CountryResponseDto();

        countryResponseDto.setId( countryModel.getId() );
        countryResponseDto.setName( countryModel.getName() );
        countryResponseDto.setCreatedAt( countryModel.getCreatedAt() );
        countryResponseDto.setUpdatedAt( countryModel.getUpdatedAt() );

        return countryResponseDto;
    }
}
