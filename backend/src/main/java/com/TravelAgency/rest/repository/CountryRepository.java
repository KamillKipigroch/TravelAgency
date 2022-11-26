package com.TravelAgency.rest.repository;

import com.TravelAgency.rest.model.country.Country;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CountryRepository extends JpaRepository<Country, Long> {
    Optional<Country> findById(Long id);


}
