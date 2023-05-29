package com.TravelAgency.rest.hotel.repository;

import com.TravelAgency.rest.hotel.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface HotelRepository extends JpaRepository<Hotel, Long> {
    @Query("SELECT o FROM Hotel o WHERE o.visible = true and o.id = :id  ")
    Optional<Hotel> findById(@Param("id") Long id);

    @Query("SELECT o FROM Hotel o WHERE o.visible = true and o.name = :name")
    Optional<Hotel> findByName(@Param("name") String name);

    @Query("SELECT o FROM Hotel o WHERE o.visible = true ")
    List<Hotel> findAll();
}
