package com.TravelAgency.rest.service;

import com.TravelAgency.rest.model.database.Country;
import com.TravelAgency.rest.model.database.Hotel;
import com.TravelAgency.rest.model.database.Offer;
import com.TravelAgency.rest.repository.HotelRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.lang.module.FindException;
import java.util.Optional;

import static com.TravelAgency.comunicates.Communicates.IS_ALREADY_EXIST;
import static com.TravelAgency.comunicates.Communicates.NOT_FOUND_WITH_ID;


@Service
@AllArgsConstructor
public class HotelService {
    private final HotelRepository hotelRepository;

    public Hotel findById(Long id) {
        return hotelRepository.findById(id)
                .orElseThrow(() -> new FindException(NOT_FOUND_WITH_ID + id));
    }
    public Optional<Hotel> findByName(String name) {
        return hotelRepository.findByName(name);
    }

    public Hotel addHotel(Hotel hotelRequest, Offer offer, Country country) {
        if (hotelRepository.findByName(hotelRequest.getName()).isPresent()) {
            throw new FindException(IS_ALREADY_EXIST);
        }
        var newObject = new Hotel();
        newObject.setCountry(country);
        newObject.setOffer(offer);
        newObject.setLat(hotelRequest.getLat());
        newObject.setLng(hotelRequest.getLng());
        newObject.setName(hotelRequest.getName());
        newObject.setStandard(hotelRequest.getStandard());
        newObject.setVisible(true);
        return hotelRepository.save(newObject);
    }
}
