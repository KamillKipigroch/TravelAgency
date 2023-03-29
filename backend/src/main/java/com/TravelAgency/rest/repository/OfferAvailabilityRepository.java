package com.TravelAgency.rest.repository;

import com.TravelAgency.rest.model.database.OfferAvailability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface OfferAvailabilityRepository extends JpaRepository<OfferAvailability, Long> {
    @Query("SELECT o FROM OfferAvailability o WHERE o.visible = true and o.id = :id  ")
    Optional<OfferAvailability> findById(@Param("id") Long id);

    @Query("SELECT o FROM OfferAvailability o WHERE o.visible = true ")
    List<OfferAvailability> findAll();
}
