package com.prevalentware.prueba_tecnica.infrastructure.output.jpa.mapper;

import com.prevalentware.prueba_tecnica.domain.model.CountryModel;
import com.prevalentware.prueba_tecnica.domain.model.RoleModel;
import com.prevalentware.prueba_tecnica.domain.model.SessionModel;
import com.prevalentware.prueba_tecnica.domain.model.UserModel;
import com.prevalentware.prueba_tecnica.domain.utils.RoleName;
import com.prevalentware.prueba_tecnica.infrastructure.output.jpa.entity.Country;
import com.prevalentware.prueba_tecnica.infrastructure.output.jpa.entity.Role;
import com.prevalentware.prueba_tecnica.infrastructure.output.jpa.entity.Session;
import com.prevalentware.prueba_tecnica.infrastructure.output.jpa.entity.User;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-12-05T18:58:50-0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.9 (Amazon.com Inc.)"
)
@Component
public class ISessionMapperImpl implements ISessionMapper {

    @Override
    public SessionModel toSessionModel(Session session) {
        if ( session == null ) {
            return null;
        }

        String id = null;
        String sessionToken = null;
        UserModel userId = null;
        LocalDateTime expiresAt = null;
        LocalDateTime createdAt = null;

        id = session.getId();
        sessionToken = session.getSessionToken();
        userId = userToUserModel( session.getUserId() );
        expiresAt = session.getExpiresAt();
        createdAt = session.getCreatedAt();

        SessionModel sessionModel = new SessionModel( id, sessionToken, userId, expiresAt, createdAt );

        return sessionModel;
    }

    protected RoleModel roleToRoleModel(Role role) {
        if ( role == null ) {
            return null;
        }

        String id = null;
        RoleName name = null;
        LocalDateTime createdAt = null;

        id = role.getId();
        name = role.getName();
        createdAt = role.getCreatedAt();

        RoleModel roleModel = new RoleModel( id, name, createdAt );

        return roleModel;
    }

    protected CountryModel countryToCountryModel(Country country) {
        if ( country == null ) {
            return null;
        }

        CountryModel countryModel = new CountryModel();

        countryModel.setId( country.getId() );
        countryModel.setName( country.getName() );
        countryModel.setCreatedAt( country.getCreatedAt() );
        countryModel.setUpdatedAt( country.getUpdatedAt() );

        return countryModel;
    }

    protected Set<CountryModel> countrySetToCountryModelSet(Set<Country> set) {
        if ( set == null ) {
            return null;
        }

        Set<CountryModel> set1 = new LinkedHashSet<CountryModel>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Country country : set ) {
            set1.add( countryToCountryModel( country ) );
        }

        return set1;
    }

    protected UserModel userToUserModel(User user) {
        if ( user == null ) {
            return null;
        }

        UserModel userModel = new UserModel();

        userModel.setId( user.getId() );
        userModel.setEmail( user.getEmail() );
        userModel.setEmailVerified( user.getEmailVerified() );
        userModel.setTermsAndConditionsAccepted( user.getTermsAndConditionsAccepted() );
        userModel.setName( user.getName() );
        userModel.setImage( user.getImage() );
        userModel.setPosition( user.getPosition() );
        userModel.setCreatedAt( user.getCreatedAt() );
        userModel.setUpdatedAt( user.getUpdatedAt() );
        userModel.setRoleId( roleToRoleModel( user.getRoleId() ) );
        userModel.setCountries( countrySetToCountryModelSet( user.getCountries() ) );

        return userModel;
    }
}
