package com.TravelAgency.offer.repository;

import com.TravelAgency.offer.model.database.BoardBasisDetailsDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BoardBasisDetailsRepository extends JpaRepository<BoardBasisDetailsDTO, Long> {
    Optional<BoardBasisDetailsDTO> findById(Long id);
}
