package com.TravelAgency.rest.offer;

import com.TravelAgency.rest.hotel.HotelRequest;
import com.TravelAgency.rest.image.ImageRequest;
import com.TravelAgency.rest.offer.offerAvailability.OfferAvailabilityRequest;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;


@NoArgsConstructor
@Data
public class OfferRequest {
    HotelRequest[] hotel;

    Long  countryId;

    OfferAvailabilityRequest availability;

    Set<ImageRequest> images;

    String description;
}
