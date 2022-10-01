package com.TravelAgency.offer.service;

import com.TravelAgency.exception.OfferNoFoundException;
import com.TravelAgency.offer.model.nregistered.Offer;
import com.TravelAgency.offer.repository.OfferRepository;
import com.neovisionaries.i18n.CountryCode;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
@AllArgsConstructor
public class OfferService {
    private final static String OFFER_NO_FOUND = "Failed to find offer with business key ";
    private final OfferRepository offerRepository;

    public Offer addOffer(Offer offer) {
        try {
            offer.setCreateDate(LocalDateTime.now());
            offer.setVisible(true);
            final String createBusinessKeyNumber = String.valueOf(offerRepository.findAll().stream()
                    .filter(or -> Objects.equals(or.getHotel().getRegion().getCountry().getCode(), offer.getHotel().getRegion().getCountry().getCode())
                            && Objects.equals(or.getCreateDate().getYear(), offer.getCreateDate().getYear())
                    ).toList().size() + 1);
            offer.setBusinessKey(
                    CountryCode.findByName(offer.getHotel().getRegion().getCountry().getCode()).get(0).name() + "/"
                            + createBusinessKeyNumber + "/" + offer.getCreateDate().getYear());
        }
        catch (Exception e){
            System.out.println("Error");
        }
        return offerRepository.save(offer);
    }

    public List<Offer> findAllOffer() {
        return offerRepository.findAll().stream().filter(Offer::getVisible).toList();
    }

    public Offer updateOffer(Offer offer) {
        return offerRepository.save(offer);
    }

    public Offer findOfferByBusinessKey(String businessKey) {
        return offerRepository.findAll().stream().findAny()
                .filter(offer -> offer.getBusinessKey() == businessKey)
                .orElseThrow(
                        () -> new OfferNoFoundException(OFFER_NO_FOUND + businessKey));
    }

    public HttpStatus deleteOffer(Long id) {
        Optional<Offer> offer = offerRepository.findById(id);
        if (offer.isPresent()) {
            offerRepository.deleteById(id);
            return HttpStatus.OK;
        }
        return HttpStatus.BAD_REQUEST;
    }
}
