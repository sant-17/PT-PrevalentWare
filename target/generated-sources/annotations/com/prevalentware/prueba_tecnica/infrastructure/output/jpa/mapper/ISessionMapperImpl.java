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
    date = "2023-12-04T16:11:23-0500",
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

        String id = null;
        String name = null;
        LocalDateTime createdAt = null;
        LocalDateTime updatedAt = null;

        id = country.getId();
        name = country.getName();
        createdAt = country.getCreatedAt();
        updatedAt = country.getUpdatedAt();

        CountryModel countryModel = new CountryModel( id, name, createdAt, updatedAt );

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

        String id = null;
        String email = null;
        LocalDateTime emailVerified = null;
        LocalDateTime termsAndConditionsAccepted = null;
        String name = null;
        String image = null;
        String position = null;
        LocalDateTime createdAt = null;
        LocalDateTime updatedAt = null;
        RoleModel roleId = null;
        Set<CountryModel> countries = null;

        id = user.getId();
        email = user.getEmail();
        emailVerified = user.getEmailVerified();
        termsAndConditionsAccepted = user.getTermsAndConditionsAccepted();
        name = user.getName();
        image = user.getImage();
        position = user.getPosition();
        createdAt = user.getCreatedAt();
        updatedAt = user.getUpdatedAt();
        roleId = roleToRoleModel( user.getRoleId() );
        countries = countrySetToCountryModelSet( user.getCountries() );

        UserModel userModel = new UserModel( id, email, emailVerified, termsAndConditionsAccepted, name, image, position, createdAt, updatedAt, roleId, countries );

        return userModel;
    }
}
