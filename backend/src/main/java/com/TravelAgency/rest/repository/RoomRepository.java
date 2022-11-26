package com.TravelAgency.rest.repository;

import com.TravelAgency.rest.model.room.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoomRepository extends JpaRepository<Room, Long> {
    Optional<Room> findById(Long id);

}
