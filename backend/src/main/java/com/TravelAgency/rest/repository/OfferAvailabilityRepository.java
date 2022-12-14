package com.TravelAgency.rest.repository;

import com.TravelAgency.rest.model.database.OfferAvailability;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OfferAvailabilityRepository extends JpaRepository<OfferAvailability, Long> {
    Optional<OfferAvailability> findById(Long id);


}
