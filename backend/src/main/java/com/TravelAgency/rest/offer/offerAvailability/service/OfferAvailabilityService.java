package com.TravelAgency.rest.offer.offerAvailability.service;

import com.TravelAgency.rest.offer.model.Offer;
import com.TravelAgency.rest.offer.offerAvailability.OfferAvailability;
import com.TravelAgency.rest.offer.offerAvailability.repository.OfferAvailabilityRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.lang.module.FindException;
import java.util.List;

import static com.TravelAgency.comunicates.Communicates.NOT_FOUND_WITH_ID;


@Service
@AllArgsConstructor
public class OfferAvailabilityService {
    private final OfferAvailabilityRepository availabilityRepository;

    public List<OfferAvailability> findAll() {
        return availabilityRepository.findAll();
    }

    public OfferAvailability findById(Long id) {
        return availabilityRepository.findById(id)
                .orElseThrow(() -> new FindException(NOT_FOUND_WITH_ID + id));
    }

    public OfferAvailability add(Offer offer, OfferAvailability offerAvailability){
        var newObject = new OfferAvailability();
        newObject.setOffer(offer);
        newObject.setPromotionPrice(offerAvailability.getPromotionPrice());
        newObject.setPromotion(offerAvailability.getPromotion());
        newObject.setDatetimeEnd(offerAvailability.getDatetimeEnd());
        newObject.setDatetimeStart(offerAvailability.getDatetimeStart());
        newObject.setVisible(true);
        newObject.setPrice(offerAvailability.getPrice());
        return availabilityRepository.save(newObject);
    }

    public OfferAvailability update(Offer offer, OfferAvailability offerAvailability){
        var newObject = new OfferAvailability();
        newObject.setOffer(offer);
        newObject.setDatetimeEnd(offerAvailability.getDatetimeEnd());
        newObject.setDatetimeStart(offerAvailability.getDatetimeStart());
        newObject.setPromotion(offerAvailability.getPromotion());
        newObject.setVisible(true);
        return availabilityRepository.save(newObject);
    }
}
