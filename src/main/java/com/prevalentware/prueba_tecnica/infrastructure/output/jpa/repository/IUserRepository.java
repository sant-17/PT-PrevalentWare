package com.prevalentware.prueba_tecnica.infrastructure.output.jpa.repository;

import com.prevalentware.prueba_tecnica.infrastructure.output.jpa.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface IUserRepository extends JpaRepository<User, String> {

    @Query(
            value = "SELECT u FROM User u WHERE u.email = ?1"
    )
    User findUserByEmail(String email);

    /**
    @Query("SELECT DISTINCT u " +
            "FROM User u " +
            "JOIN _CountryToUser ctu ON u.id = ctu.B " +
            "JOIN UserMonitoring um ON u.id = um.userId.id " +
            "WHERE ctu.A.id = ?1 " +
            "AND um.description = ?2 " +
            "AND um.createdAt BETWEEN ?3 AND ?4")
    List<User> findUsersByUsageAndCountryAndTimeRange(String countryId, String description, LocalDateTime from, LocalDateTime to, Pageable pageable);
    **/
}
