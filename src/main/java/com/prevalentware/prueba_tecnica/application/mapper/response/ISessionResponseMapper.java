package com.prevalentware.prueba_tecnica.application.mapper.response;

import com.prevalentware.prueba_tecnica.application.dto.response.SessionResponseDto;
import com.prevalentware.prueba_tecnica.domain.model.SessionModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface ISessionResponseMapper {
    SessionResponseDto toResponse(SessionModel sessionModel);
}
