package com.TravelAgency.rest.image.opinion.repository;

import com.TravelAgency.rest.image.opinion.model.OpinionImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface OpinionImageRepository extends JpaRepository<OpinionImage, Long> {
    @Query("SELECT o FROM Hotel o WHERE o.visible = true and o.id = :id")
    Optional<OpinionImage> findById(@Param("id") Long id);

    @Query("SELECT o FROM Hotel o WHERE o.visible = true ")
    List<OpinionImage> findAll();
}
