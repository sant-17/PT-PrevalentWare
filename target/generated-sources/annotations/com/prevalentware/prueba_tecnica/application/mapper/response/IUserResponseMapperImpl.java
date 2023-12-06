package com.prevalentware.prueba_tecnica.application.mapper.response;

import com.prevalentware.prueba_tecnica.application.dto.response.CountryResponseDto;
import com.prevalentware.prueba_tecnica.application.dto.response.RoleResponseDto;
import com.prevalentware.prueba_tecnica.application.dto.response.TopUsersResponseDto;
import com.prevalentware.prueba_tecnica.application.dto.response.UserResponseDto;
import com.prevalentware.prueba_tecnica.domain.model.CountryModel;
import com.prevalentware.prueba_tecnica.domain.model.RoleModel;
import com.prevalentware.prueba_tecnica.domain.model.UserModel;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-12-05T19:01:52-0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.9 (Eclipse Adoptium)"
)
@Component
public class IUserResponseMapperImpl implements IUserResponseMapper {

    @Override
    public List<UserResponseDto> toResponseList(List<UserModel> userModelList) {
        if ( userModelList == null ) {
            return null;
        }

        List<UserResponseDto> list = new ArrayList<UserResponseDto>( userModelList.size() );
        for ( UserModel userModel : userModelList ) {
            list.add( toResponse( userModel ) );
        }

        return list;
    }

    @Override
    public UserResponseDto toResponse(UserModel userModel) {
        if ( userModel == null ) {
            return null;
        }

        UserResponseDto userResponseDto = new UserResponseDto();

        userResponseDto.setId( userModel.getId() );
        userResponseDto.setEmail( userModel.getEmail() );
        userResponseDto.setEmailVerified( userModel.getEmailVerified() );
        userResponseDto.setTermsAndConditionsAccepted( userModel.getTermsAndConditionsAccepted() );
        userResponseDto.setName( userModel.getName() );
        userResponseDto.setImage( userModel.getImage() );
        userResponseDto.setPosition( userModel.getPosition() );
        userResponseDto.setCreatedAt( userModel.getCreatedAt() );
        userResponseDto.setUpdatedAt( userModel.getUpdatedAt() );
        userResponseDto.setRoleId( roleModelToRoleResponseDto( userModel.getRoleId() ) );
        userResponseDto.setCountries( countryModelSetToCountryResponseDtoSet( userModel.getCountries() ) );

        return userResponseDto;
    }

    @Override
    public TopUsersResponseDto toTopResponse(UserModel userModel) {
        if ( userModel == null ) {
            return null;
        }

        TopUsersResponseDto topUsersResponseDto = new TopUsersResponseDto();

        topUsersResponseDto.setId( userModel.getId() );
        topUsersResponseDto.setEmail( userModel.getEmail() );
        topUsersResponseDto.setName( userModel.getName() );
        topUsersResponseDto.setCreatedAt( userModel.getCreatedAt() );
        topUsersResponseDto.setRoleId( roleModelToRoleResponseDto( userModel.getRoleId() ) );

        return topUsersResponseDto;
    }

    @Override
    public List<TopUsersResponseDto> toTopResponseList(List<UserModel> userModelList) {
        if ( userModelList == null ) {
            return null;
        }

        List<TopUsersResponseDto> list = new ArrayList<TopUsersResponseDto>( userModelList.size() );
        for ( UserModel userModel : userModelList ) {
            list.add( toTopResponse( userModel ) );
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

    protected Set<CountryResponseDto> countryModelSetToCountryResponseDtoSet(Set<CountryModel> set) {
        if ( set == null ) {
            return null;
        }

        Set<CountryResponseDto> set1 = new LinkedHashSet<CountryResponseDto>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( CountryModel countryModel : set ) {
            set1.add( countryModelToCountryResponseDto( countryModel ) );
        }

        return set1;
    }
}
