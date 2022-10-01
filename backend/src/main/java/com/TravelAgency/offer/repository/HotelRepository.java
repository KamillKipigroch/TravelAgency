package com.TravelAgency.offer.repository;

import com.TravelAgency.offer.model.nregistered.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface HotelRepository extends JpaRepository<Hotel, Long> {
    Optional<Hotel> findById(Long id);
}
