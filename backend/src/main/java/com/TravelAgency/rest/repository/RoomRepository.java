package com.TravelAgency.rest.repository;

import com.TravelAgency.rest.model.database.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RoomRepository extends JpaRepository<Room, Long> {
    Optional<Room> findById(Long id);

    Optional<List<Room>> findByHotelId(Long hotelId);
}
