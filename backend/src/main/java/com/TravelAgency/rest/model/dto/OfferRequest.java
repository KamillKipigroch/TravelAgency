package com.TravelAgency.rest.model.dto;

import lombok.*;

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
