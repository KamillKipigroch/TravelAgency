package com.TravelAgency.rest.repository;

import com.TravelAgency.rest.model.database.RoomDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RoomDetailRepository extends JpaRepository<RoomDetail, Long> {
    @Query("SELECT o FROM RoomDetail o WHERE o.visible = true  and o.id = :id")
    Optional<RoomDetail> findById(@Param("id") Long id);

    @Query("SELECT o FROM RoomDetail o WHERE o.visible = true and o.name = :name ")
    Optional<RoomDetail> findByName(@Param("name") String name);

    @Query("SELECT o FROM RoomDetail o WHERE o.visible = true ")
    List<RoomDetail> findAll();
}
