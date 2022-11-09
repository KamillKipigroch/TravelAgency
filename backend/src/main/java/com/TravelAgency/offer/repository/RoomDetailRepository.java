package com.TravelAgency.offer.repository;

import com.TravelAgency.offer.model.database.RoomDTO;
import com.TravelAgency.offer.model.database.RoomDetailsDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoomDetailRepository extends JpaRepository<RoomDetailsDTO, Long> {
    Optional<RoomDetailsDTO> findById(Long id);
    Optional<RoomDetailsDTO> findByCode(String code);
}
