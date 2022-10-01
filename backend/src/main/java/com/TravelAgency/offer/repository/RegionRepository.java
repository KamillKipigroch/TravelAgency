package com.TravelAgency.offer.repository;

import com.TravelAgency.offer.model.nregistered.Region;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface RegionRepository extends JpaRepository<Region, Long> {
    Optional<Region> findById(Long id);
}
