package com.prevalentware.prueba_tecnica.infrastructure.output.jpa.mapper;

import com.prevalentware.prueba_tecnica.domain.model.CountryModel;
import com.prevalentware.prueba_tecnica.domain.model.RoleModel;
import com.prevalentware.prueba_tecnica.domain.model.UserModel;
import com.prevalentware.prueba_tecnica.domain.utils.RoleName;
import com.prevalentware.prueba_tecnica.infrastructure.output.jpa.entity.Country;
import com.prevalentware.prueba_tecnica.infrastructure.output.jpa.entity.Role;
import com.prevalentware.prueba_tecnica.infrastructure.output.jpa.entity.User;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-12-05T01:36:22-0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.9 (Amazon.com Inc.)"
)
@Component
public class IUserMapperImpl implements IUserMapper {

    @Override
    public UserModel toUserModel(User user) {
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

    @Override
    public User toUser(UserModel userModel) {
        if ( userModel == null ) {
            return null;
        }

        User user = new User();

        user.setId( userModel.getId() );
        user.setEmail( userModel.getEmail() );
        user.setEmailVerified( userModel.getEmailVerified() );
        user.setTermsAndConditionsAccepted( userModel.getTermsAndConditionsAccepted() );
        user.setName( userModel.getName() );
        user.setImage( userModel.getImage() );
        user.setPosition( userModel.getPosition() );
        user.setCreatedAt( userModel.getCreatedAt() );
        user.setUpdatedAt( userModel.getUpdatedAt() );
        user.setRoleId( roleModelToRole( userModel.getRoleId() ) );
        user.setCountries( countryModelSetToCountrySet( userModel.getCountries() ) );

        return user;
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

    protected Role roleModelToRole(RoleModel roleModel) {
        if ( roleModel == null ) {
            return null;
        }

        Role role = new Role();

        role.setId( roleModel.getId() );
        role.setName( roleModel.getName() );
        role.setCreatedAt( roleModel.getCreatedAt() );

        return role;
    }

    protected Country countryModelToCountry(CountryModel countryModel) {
        if ( countryModel == null ) {
            return null;
        }

        Country country = new Country();

        country.setId( countryModel.getId() );
        country.setName( countryModel.getName() );
        country.setCreatedAt( countryModel.getCreatedAt() );
        country.setUpdatedAt( countryModel.getUpdatedAt() );

        return country;
    }

    protected Set<Country> countryModelSetToCountrySet(Set<CountryModel> set) {
        if ( set == null ) {
            return null;
        }

        Set<Country> set1 = new LinkedHashSet<Country>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( CountryModel countryModel : set ) {
            set1.add( countryModelToCountry( countryModel ) );
        }

        return set1;
    }
}
