package com.TravelAgency.rest.service;

import com.TravelAgency.rest.model.country.Country;
import com.TravelAgency.rest.model.hotel.Hotel;
import com.TravelAgency.rest.model.offer.Offer;
import com.TravelAgency.rest.model.offer.OfferRequest;
import com.TravelAgency.rest.model.opinion.Opinion;
import com.TravelAgency.rest.model.opinion.OpinionRequest;
import com.TravelAgency.rest.repository.OfferRepository;
import com.TravelAgency.security.user.model.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.lang.module.FindException;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static com.TravelAgency.comunicates.Communicates.*;


@Service
@AllArgsConstructor
public class OfferService {
    private final OfferRepository offerRepository;

    public List<Offer> findAll() {
        var offers = offerRepository.findAll();
        offers.forEach(offer -> offer.setAvailabilities(
                offer.getAvailabilities().stream().filter(availability -> availability.getDatetimeStart().isAfter(ChronoLocalDate.from(LocalDateTime.now()))).collect(Collectors.toSet())));
        return offers;
    }

    public Offer findById(Long id) {
        return offerRepository.findById(id)
                .orElseThrow(() -> new FindException(NOT_FOUND_WITH_ID + id));
    }


    public Offer add(OfferRequest offerRequest, Country country) {
        var newObject = new Offer();
        newObject.setCountry(country);
        newObject.setDescription(offerRequest.getDescription());
        newObject.setCreateDate(LocalDateTime.now());
        newObject.setVisible(true);
        return offerRepository.save(newObject);
    }

    public void delete(Long id) {
        var object = offerRepository.findById(id).orElseThrow(() ->
                new FindException(NOT_FOUND_WITH_ID + id));
        object.setVisible(false);
        offerRepository.save(object);
    }
}
