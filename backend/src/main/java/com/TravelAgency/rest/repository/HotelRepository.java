package com.TravelAgency.rest.repository;

import com.TravelAgency.rest.model.database.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HotelRepository extends JpaRepository<Hotel, Long> {
    Optional<Hotel> findById(Long id);
    Optional<Hotel> findByName(String name);
}
