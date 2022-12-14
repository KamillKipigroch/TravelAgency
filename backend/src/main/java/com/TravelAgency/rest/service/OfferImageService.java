package com.TravelAgency.rest.service;

import com.TravelAgency.rest.model.database.OfferImage;
import com.TravelAgency.rest.model.database.Offer;
import com.TravelAgency.rest.repository.OfferImageRepository;
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
