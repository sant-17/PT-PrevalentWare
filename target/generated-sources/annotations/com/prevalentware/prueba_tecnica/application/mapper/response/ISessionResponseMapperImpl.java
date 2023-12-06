package com.prevalentware.prueba_tecnica.application.mapper.response;

import com.prevalentware.prueba_tecnica.application.dto.response.CountryResponseDto;
import com.prevalentware.prueba_tecnica.application.dto.response.RoleResponseDto;
import com.prevalentware.prueba_tecnica.application.dto.response.SessionResponseDto;
import com.prevalentware.prueba_tecnica.application.dto.response.UserResponseDto;
import com.prevalentware.prueba_tecnica.domain.model.CountryModel;
import com.prevalentware.prueba_tecnica.domain.model.RoleModel;
import com.prevalentware.prueba_tecnica.domain.model.SessionModel;
import com.prevalentware.prueba_tecnica.domain.model.UserModel;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-12-05T19:52:13-0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.9 (Eclipse Adoptium)"
)
@Component
public class ISessionResponseMapperImpl implements ISessionResponseMapper {

    @Override
    public SessionResponseDto toResponse(SessionModel sessionModel) {
        if ( sessionModel == null ) {
            return null;
        }

        SessionResponseDto sessionResponseDto = new SessionResponseDto();

        sessionResponseDto.setId( sessionModel.getId() );
        sessionResponseDto.setSessionToken( sessionModel.getSessionToken() );
        sessionResponseDto.setUserId( userModelToUserResponseDto( sessionModel.getUserId() ) );
        sessionResponseDto.setExpiresAt( sessionModel.getExpiresAt() );
        sessionResponseDto.setCreatedAt( sessionModel.getCreatedAt() );

        return sessionResponseDto;
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

    protected UserResponseDto userModelToUserResponseDto(UserModel userModel) {
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
}
