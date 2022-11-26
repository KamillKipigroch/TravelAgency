package com.TravelAgency.rest.repository;

import com.TravelAgency.rest.model.hotelImage.HotelImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HoteImageRepository extends JpaRepository<HotelImage, Long> {
    Optional<HotelImage> findById(Long id);


}
