package com.TravelAgency.rest.repository;

import com.TravelAgency.rest.model.opinionImage.OpinionImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OpinionImageRepository extends JpaRepository<OpinionImage, Long> {
    Optional<OpinionImage> findById(Long id);


}
