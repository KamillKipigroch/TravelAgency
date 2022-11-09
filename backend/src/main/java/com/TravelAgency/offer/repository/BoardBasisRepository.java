package com.TravelAgency.offer.repository;

import com.TravelAgency.offer.model.database.BoardBasisDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BoardBasisRepository extends JpaRepository<BoardBasisDTO, Long> {
    Optional<BoardBasisDTO> findById(Long id);
}
