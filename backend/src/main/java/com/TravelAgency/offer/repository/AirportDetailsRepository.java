package com.TravelAgency.offer.repository;

import com.TravelAgency.offer.model.database.AirportDetailsDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AirportDetailsRepository extends JpaRepository<AirportDetailsDTO, Long> {
    Optional<AirportDetailsDTO> findById(Long id);
    Optional<AirportDetailsDTO> findByCode (String code);
}
