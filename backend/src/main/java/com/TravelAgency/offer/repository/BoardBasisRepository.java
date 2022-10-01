package com.TravelAgency.offer.repository;

import com.TravelAgency.offer.model.nregistered.BoardBasis;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface BoardBasisRepository extends JpaRepository<BoardBasis, Long> {
    Optional<BoardBasis> findById(Long id);
}
