package com.TravelAgency.rest.repository;

import com.TravelAgency.rest.model.database.OfferImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface OfferImageRepository extends JpaRepository<OfferImage, Long> {
    @Query("SELECT o FROM OfferImage o WHERE o.visible = true and o.id = :id ")
    Optional<OfferImage> findById(@Param("id") Long id);

    @Query("SELECT o FROM OfferImage o WHERE o.visible = true ")
    List<OfferImage> findAll();
}
