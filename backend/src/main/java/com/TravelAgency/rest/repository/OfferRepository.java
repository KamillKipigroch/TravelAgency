package com.TravelAgency.rest.repository;

import com.TravelAgency.rest.model.offer.Offer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OfferRepository extends JpaRepository<Offer, Long> {
    Optional<Offer> findById(Long id);


}
