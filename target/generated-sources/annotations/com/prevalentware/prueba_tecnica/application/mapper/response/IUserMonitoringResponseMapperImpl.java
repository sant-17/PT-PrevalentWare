package com.prevalentware.prueba_tecnica.application.mapper.response;

import com.prevalentware.prueba_tecnica.application.dto.response.UserMonitoringResponseDto;
import com.prevalentware.prueba_tecnica.domain.model.UserModel;
import com.prevalentware.prueba_tecnica.domain.model.UserMonitoringModel;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-12-05T19:01:52-0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.9 (Eclipse Adoptium)"
)
@Component
public class IUserMonitoringResponseMapperImpl implements IUserMonitoringResponseMapper {

    @Override
    public UserMonitoringResponseDto toResponse(UserMonitoringModel userMonitoringModel) {
        if ( userMonitoringModel == null ) {
            return null;
        }

        UserMonitoringResponseDto userMonitoringResponseDto = new UserMonitoringResponseDto();

        userMonitoringResponseDto.setUserId( userMonitoringModelUserIdId( userMonitoringModel ) );
        userMonitoringResponseDto.setId( userMonitoringModel.getId() );
        userMonitoringResponseDto.setUsage( userMonitoringModel.getUsage() );
        userMonitoringResponseDto.setDescription( userMonitoringModel.getDescription() );
        userMonitoringResponseDto.setCreatedAt( userMonitoringModel.getCreatedAt() );

        return userMonitoringResponseDto;
    }

    @Override
    public List<UserMonitoringResponseDto> toResponseList(List<UserMonitoringModel> userMonitoringModelList) {
        if ( userMonitoringModelList == null ) {
            return null;
        }

        List<UserMonitoringResponseDto> list = new ArrayList<UserMonitoringResponseDto>( userMonitoringModelList.size() );
        for ( UserMonitoringModel userMonitoringModel : userMonitoringModelList ) {
            list.add( toResponse( userMonitoringModel ) );
        }

        return list;
    }

    private String userMonitoringModelUserIdId(UserMonitoringModel userMonitoringModel) {
        if ( userMonitoringModel == null ) {
            return null;
        }
        UserModel userId = userMonitoringModel.getUserId();
        if ( userId == null ) {
            return null;
        }
        String id = userId.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
