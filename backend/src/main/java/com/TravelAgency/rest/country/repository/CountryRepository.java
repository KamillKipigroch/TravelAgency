package com.TravelAgency.rest.country.repository;

import com.TravelAgency.rest.country.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CountryRepository extends JpaRepository<Country, Long> {
    @Query("SELECT o FROM Country o WHERE o.visible = true and o.id = :id ")
    Optional<Country> findById(@Param("id") Long id);

    @Query("SELECT o FROM Country o WHERE o.visible = true ")
    List<Country> findAll();
}
