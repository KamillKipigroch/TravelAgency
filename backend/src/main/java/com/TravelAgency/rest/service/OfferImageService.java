package com.TravelAgency.rest.service;

import com.TravelAgency.rest.model.ImageRequest;
import com.TravelAgency.rest.model.offerImage.OfferImage;
import com.TravelAgency.rest.model.offer.Offer;
import com.TravelAgency.rest.repository.OfferImageRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class OfferImageService {
    private final OfferImageRepository offerImageRepository;


    public OfferImage add(Offer offer, ImageRequest image){
        var newObject = new OfferImage();
        newObject.setVisible(true);
        newObject.setOffer(offer);
        newObject.setName(image.getUrl());
        return offerImageRepository.save(newObject);
    }
}
