package com.prevalentware.prueba_tecnica.application.mapper.response;

import com.prevalentware.prueba_tecnica.application.dto.response.UserMonitoringResponseDto;
import com.prevalentware.prueba_tecnica.domain.model.UserMonitoringModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface IUserMonitoringResponseMapper {

    @Mapping(target = "userId", source = "userId.id")
    UserMonitoringResponseDto toResponse(UserMonitoringModel userMonitoringModel);
    List<UserMonitoringResponseDto> toResponseList(List<UserMonitoringModel> userMonitoringModelList);
}
