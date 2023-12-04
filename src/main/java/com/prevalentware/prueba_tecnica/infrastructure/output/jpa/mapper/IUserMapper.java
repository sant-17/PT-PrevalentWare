package com.prevalentware.prueba_tecnica.infrastructure.output.jpa.mapper;

import com.prevalentware.prueba_tecnica.domain.model.UserModel;
import com.prevalentware.prueba_tecnica.infrastructure.output.jpa.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface IUserMapper {
    UserModel toUserModel(User user);
    User toUser(UserModel userModel);
}
