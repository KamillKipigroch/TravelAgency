package com.TravelAgency.rest.hotel.service;

import com.TravelAgency.rest.country.model.Country;
import com.TravelAgency.rest.hotel.model.Hotel;
import com.TravelAgency.rest.hotel.repository.HotelRepository;
import com.TravelAgency.rest.offer.model.Offer;
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
        newObject.setName(hotelRequest.getName());
        newObject.setStandard(hotelRequest.getStandard());
        newObject.setVisible(true);
        return hotelRepository.save(newObject);
    }
}
