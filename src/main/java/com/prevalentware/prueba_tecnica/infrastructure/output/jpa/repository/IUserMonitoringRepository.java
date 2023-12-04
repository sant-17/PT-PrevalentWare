package com.prevalentware.prueba_tecnica.infrastructure.output.jpa.repository;

import com.prevalentware.prueba_tecnica.infrastructure.output.jpa.entity.User;
import com.prevalentware.prueba_tecnica.infrastructure.output.jpa.entity.UserMonitoring;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.data.domain.Pageable;
import java.time.LocalDateTime;
import java.util.List;

public interface IUserMonitoringRepository extends JpaRepository<UserMonitoring, String> {

    @Query(
            value = "SELECT m from UserMonitoring m WHERE m.userId = ?1 AND (m.createdAt BETWEEN ?2 AND ?3)"
    )
    List<UserMonitoring> findUserMonitoringByUserIdBetweenDates(User user, LocalDateTime from, LocalDateTime to);

    @Query("SELECT u.userId FROM UserMonitoring u " +
            "WHERE u.createdAt BETWEEN ?1 AND ?2 " +
            "GROUP BY u.userId " +
            "ORDER BY COUNT(u) DESC")
    List<User> findTopUsersByMonitoring(LocalDateTime from, LocalDateTime to, Pageable pageable);
}
