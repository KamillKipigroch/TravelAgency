package com.TravelAgency.rest.image.offer.service;

import com.TravelAgency.rest.image.offer.model.OfferImage;
import com.TravelAgency.rest.image.offer.repository.OfferImageRepository;
import com.TravelAgency.rest.offer.model.Offer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class OfferImageService {
    private final OfferImageRepository offerImageRepository;


    public OfferImage add(Offer offer, String image){
        var newObject = new OfferImage();
        newObject.setVisible(true);
        newObject.setOffer(offer);
        newObject.setName(image);
        return offerImageRepository.save(newObject);
    }
}
