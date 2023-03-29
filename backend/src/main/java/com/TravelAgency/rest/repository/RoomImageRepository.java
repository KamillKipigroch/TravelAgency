package com.TravelAgency.rest.repository;

import com.TravelAgency.rest.model.database.RoomImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RoomImageRepository extends JpaRepository<RoomImage, Long> {
    @Query("SELECT o FROM RoomImage o WHERE o.visible = true  and o.id = :id")
    Optional<RoomImage> findById(@Param("id") Long id);

    @Query("SELECT o FROM RoomImage o WHERE o.visible = true ")
    List<RoomImage> findAll();
}
