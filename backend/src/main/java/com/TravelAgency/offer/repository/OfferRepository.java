package com.TravelAgency.offer.repository;

import com.TravelAgency.offer.model.database.OfferDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OfferRepository extends JpaRepository<OfferDTO, Long> {
    Optional<OfferDTO> findById(Long id);
}
