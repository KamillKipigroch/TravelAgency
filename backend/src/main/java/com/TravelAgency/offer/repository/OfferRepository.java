package com.TravelAgency.offer.repository;

import com.TravelAgency.offer.model.nregistered.Offer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface OfferRepository extends JpaRepository<Offer, Long> {
    Optional<Offer> findById(Long id);
}
