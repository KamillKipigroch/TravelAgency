package com.TravelAgency.rest.repository;

import com.TravelAgency.rest.model.database.RoomDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoomDetailRepository extends JpaRepository<RoomDetail, Long> {
    Optional<RoomDetail> findById(Long id);

    Optional<RoomDetail> findByName(String name);

}
