package com.prevalentware.prueba_tecnica.application.mapper.response;

import com.prevalentware.prueba_tecnica.application.dto.response.CountryResponseDto;
import com.prevalentware.prueba_tecnica.domain.model.CountryModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(
        componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface ICountryResponseMapper {
    List<CountryResponseDto> toResponseList(List<CountryModel> countryModelList);
}
