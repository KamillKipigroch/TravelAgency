package com.TravelAgency.offer.repository;

import com.TravelAgency.offer.model.nregistered.Country;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface CountryRepository extends JpaRepository<Country, Long> {
    Optional<Country> findById(Long id);
}
