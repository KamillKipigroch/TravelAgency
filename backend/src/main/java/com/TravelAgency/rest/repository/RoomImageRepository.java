package com.TravelAgency.rest.repository;

import com.TravelAgency.rest.model.roomImage.RoomImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoomImageRepository extends JpaRepository<RoomImage, Long> {
    Optional<RoomImage> findById(Long id);
    Optional<RoomImage> findByName(String name);
}
