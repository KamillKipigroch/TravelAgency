package com.TravelAgency.offer.repository;

import com.TravelAgency.offer.model.database.OfferDetailsDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OfferDetailsRepository extends JpaRepository<OfferDetailsDTO, Long> {
    Optional<OfferDetailsDTO> findById(Long id);
}
