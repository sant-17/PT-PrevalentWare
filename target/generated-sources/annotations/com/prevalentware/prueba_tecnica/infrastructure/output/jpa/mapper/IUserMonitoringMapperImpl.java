package com.prevalentware.prueba_tecnica.infrastructure.output.jpa.mapper;

import com.prevalentware.prueba_tecnica.domain.model.CountryModel;
import com.prevalentware.prueba_tecnica.domain.model.RoleModel;
import com.prevalentware.prueba_tecnica.domain.model.UserModel;
import com.prevalentware.prueba_tecnica.domain.model.UserMonitoringModel;
import com.prevalentware.prueba_tecnica.domain.utils.RoleName;
import com.prevalentware.prueba_tecnica.infrastructure.output.jpa.entity.Country;
import com.prevalentware.prueba_tecnica.infrastructure.output.jpa.entity.Role;
import com.prevalentware.prueba_tecnica.infrastructure.output.jpa.entity.User;
import com.prevalentware.prueba_tecnica.infrastructure.output.jpa.entity.UserMonitoring;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-12-05T01:36:21-0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.9 (Amazon.com Inc.)"
)
@Component
public class IUserMonitoringMapperImpl implements IUserMonitoringMapper {

    @Override
    public UserMonitoringModel toUserMonitoringModel(UserMonitoring userMonitoring) {
        if ( userMonitoring == null ) {
            return null;
        }

        UserMonitoringModel userMonitoringModel = new UserMonitoringModel();

        userMonitoringModel.setId( userMonitoring.getId() );
        userMonitoringModel.setUsage( userMonitoring.getUsage() );
        userMonitoringModel.setDescription( userMonitoring.getDescription() );
        userMonitoringModel.setUserId( userToUserModel( userMonitoring.getUserId() ) );
        userMonitoringModel.setCreatedAt( userMonitoring.getCreatedAt() );

        return userMonitoringModel;
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
