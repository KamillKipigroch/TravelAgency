package com.TravelAgency.rest.repository;

import com.TravelAgency.rest.model.database.OfferImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OfferImageRepository extends JpaRepository<OfferImage, Long> {
    Optional<OfferImage> findById(Long id);
}
