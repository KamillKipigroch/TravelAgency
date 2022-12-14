package com.TravelAgency.rest.repository;

import com.TravelAgency.rest.model.database.Offer;
import com.TravelAgency.rest.model.database.Opinion;
import com.TravelAgency.security.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface OpinionRepository extends JpaRepository<Opinion, Long> {
    Optional<Opinion> findById(Long id);

    @Query("SELECT o FROM Opinion o WHERE o.user = :user and o.offer = :offer")
    Optional<Opinion> findOpinionByUserAndOffer(
            @Param("user") User user,
            @Param("offer") Offer offer);


}
