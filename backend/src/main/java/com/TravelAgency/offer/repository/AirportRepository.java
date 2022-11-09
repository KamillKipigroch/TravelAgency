package com.TravelAgency.offer.repository;

import com.TravelAgency.offer.model.database.AirportDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AirportRepository extends JpaRepository<AirportDTO, Long> {
    Optional<AirportDTO> findById(Long id);
    Optional<AirportDTO> findByBusinessKey(String businessKey);
}
