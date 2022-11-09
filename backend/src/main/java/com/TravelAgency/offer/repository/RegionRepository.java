package com.TravelAgency.offer.repository;

import com.TravelAgency.offer.model.database.CountryDTO;
import com.TravelAgency.offer.model.database.RegionDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RegionRepository extends JpaRepository<RegionDTO, Long> {
    Optional<RegionDTO> findById(Long id);
}
