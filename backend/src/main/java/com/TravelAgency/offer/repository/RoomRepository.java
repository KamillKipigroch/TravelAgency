package com.TravelAgency.offer.repository;

import com.TravelAgency.offer.model.database.HotelDTO;
import com.TravelAgency.offer.model.database.RoomDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoomRepository extends JpaRepository<RoomDTO, Long> {
    Optional<RoomDTO> findById(Long id);
}
