package com.TravelAgency.offer.repository;

import com.TravelAgency.offer.model.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface OfferRepository extends JpaRepository<Offer, Long> {
    Optional<Offer> findById(Long id);
}
