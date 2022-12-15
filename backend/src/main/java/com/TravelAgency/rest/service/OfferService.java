package com.TravelAgency.rest.service;

import com.TravelAgency.rest.model.database.Country;
import com.TravelAgency.rest.model.database.Offer;
import com.TravelAgency.rest.repository.OfferRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.lang.module.FindException;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDate;
import java.util.List;

import static com.TravelAgency.comunicates.Communicates.*;


@Service
@AllArgsConstructor
public class OfferService {
    private final OfferRepository offerRepository;

    public List<Offer> findAll() {
        var offers = offerRepository.findAll();
        offers.forEach(offer -> offer.setAvailabilities(
                offer.getAvailabilities().stream().filter(availability -> availability.getDatetimeStart().isAfter(ChronoLocalDate.from(LocalDateTime.now()))).toList()));
        return offers;
    }

    public Offer findById(Long id) {
        return offerRepository.findById(id)
                .orElseThrow(() -> new FindException(NOT_FOUND_WITH_ID + id));
    }


    public Offer add(Offer offerRequest) {
        var newObject = new Offer();
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

    public Offer update(Offer request) {
        offerRepository.findById(request.getId()).orElseThrow(() ->
                new FindException(NOT_FOUND_WITH_ID + request.getId()));
        return offerRepository.save(request);
    }
}
