package com.TravelAgency.offer.repository;

import com.TravelAgency.offer.model.database.CountryDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CountryRepository extends JpaRepository<CountryDTO, Long> {
    Optional<CountryDTO> findById(Long id);
    Optional<CountryDTO> findByCode(String code);
}
