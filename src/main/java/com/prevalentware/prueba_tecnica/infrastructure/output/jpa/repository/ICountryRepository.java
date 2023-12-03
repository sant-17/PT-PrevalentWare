package com.prevalentware.prueba_tecnica.infrastructure.output.jpa.repository;

import com.prevalentware.prueba_tecnica.infrastructure.output.jpa.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ICountryRepository extends JpaRepository<Country, String> {

    @Query(
            value = "SELECT * FROM Country",
            nativeQuery = true
    )
    List<Country> getAllCountries();
}
