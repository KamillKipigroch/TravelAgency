package com.TravelAgency.offer.repository;

import com.TravelAgency.offer.model.database.HotelDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HotelRepository extends JpaRepository<HotelDTO, Long> {
    Optional<HotelDTO> findById(Long id);
    Optional<HotelDTO> findByName(String name);
}
