package com.prevalentware.prueba_tecnica.application.mapper.response;

import com.prevalentware.prueba_tecnica.application.dto.response.RoleResponseDto;
import com.prevalentware.prueba_tecnica.domain.model.RoleModel;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-12-04T00:44:18-0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.9 (Amazon.com Inc.)"
)
@Component
public class IRoleResponseMapperImpl implements IRoleResponseMapper {

    @Override
    public List<RoleResponseDto> toResponseList(List<RoleModel> roleModelList) {
        if ( roleModelList == null ) {
            return null;
        }

        List<RoleResponseDto> list = new ArrayList<RoleResponseDto>( roleModelList.size() );
        for ( RoleModel roleModel : roleModelList ) {
            list.add( roleModelToRoleResponseDto( roleModel ) );
        }

        return list;
    }

    protected RoleResponseDto roleModelToRoleResponseDto(RoleModel roleModel) {
        if ( roleModel == null ) {
            return null;
        }

        RoleResponseDto roleResponseDto = new RoleResponseDto();

        roleResponseDto.setId( roleModel.getId() );
        roleResponseDto.setName( roleModel.getName() );
        roleResponseDto.setCreatedAt( roleModel.getCreatedAt() );

        return roleResponseDto;
    }
}
