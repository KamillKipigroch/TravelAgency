package com.TravelAgency.rest.repository;

import com.TravelAgency.rest.model.database.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RoomRepository extends JpaRepository<Room, Long> {
    @Query("SELECT o FROM Room o WHERE o.visible = true  and o.id = :id")
    Optional<Room> findById(@Param("id") Long id);

    @Query("SELECT o FROM Room o WHERE o.visible = true and o.hotel.id = :hotelId ")
    Optional<List<Room>> findByHotelId(@Param("hotelId") Long hotelId);

    @Query("SELECT o FROM Room o WHERE o.visible = true ")
    List<Room> findAll();
}
