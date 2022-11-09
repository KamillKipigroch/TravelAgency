package com.TravelAgency.offer.repository;

import com.TravelAgency.offer.model.database.AvailabilityDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AvailabilityRepository extends JpaRepository<AvailabilityDTO, Long> {
    Optional<AvailabilityDTO> findById(Long id);
}
