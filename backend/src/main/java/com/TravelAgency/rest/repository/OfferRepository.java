package com.TravelAgency.rest.repository;

import com.TravelAgency.rest.model.database.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface OfferRepository extends JpaRepository<Offer, Long> {
    @Query("SELECT o FROM Offer o WHERE o.visible = true and o.id = :id ")
    Optional<Offer> findById(@Param("id") Long id);

    @Query("SELECT o FROM Offer o WHERE o.visible = true ")
    List<Offer> findAll();
}
