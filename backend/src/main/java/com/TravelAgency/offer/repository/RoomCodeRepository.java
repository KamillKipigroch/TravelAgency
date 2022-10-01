package com.TravelAgency.offer.repository;

import com.TravelAgency.offer.model.nregistered.RoomDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface RoomCodeRepository extends JpaRepository<RoomDetails, Long> {
    Optional<RoomDetails> findById(Long id);
}
