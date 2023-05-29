package com.TravelAgency.rest.opinion.repository;

import com.TravelAgency.rest.offer.model.Offer;
import com.TravelAgency.rest.opinion.model.Opinion;
import com.TravelAgency.rest.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface OpinionRepository extends JpaRepository<Opinion, Long> {
    @Query("SELECT o FROM Opinion o WHERE o.visible = true and o.id = :id")
    Optional<Opinion> findById(@Param("id") Long id);

    @Query("SELECT o FROM Opinion o WHERE o.user = :user and o.offer = :offer and  o.visible = true")
    Optional<Opinion> findOpinionByUserAndOffer(
            @Param("user") User user,
            @Param("offer") Offer offer);

    @Query("SELECT o FROM Opinion o WHERE o.visible = true ")
    List<Opinion> findAll();
}
