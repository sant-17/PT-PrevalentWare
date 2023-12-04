package com.prevalentware.prueba_tecnica.infrastructure.output.jpa.mapper;

import com.prevalentware.prueba_tecnica.domain.model.UserMonitoringModel;
import com.prevalentware.prueba_tecnica.infrastructure.output.jpa.entity.UserMonitoring;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface IUserMonitoringMapper {
    UserMonitoringModel toUserMonitoringModel(UserMonitoring userMonitoring);
}
