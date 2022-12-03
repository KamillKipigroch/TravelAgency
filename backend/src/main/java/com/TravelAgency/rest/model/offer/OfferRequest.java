package com.TravelAgency.rest.model.offer;

import com.TravelAgency.rest.model.ImageRequest;
import com.TravelAgency.rest.model.hotel.Hotel;
import com.TravelAgency.rest.model.hotel.HotelRequest;
import com.TravelAgency.rest.model.offerAvailability.OfferAvailabilityRequest;
import com.TravelAgency.rest.model.offerImage.OfferImage;
import com.TravelAgency.rest.model.opinion.Opinion;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;


@NoArgsConstructor
@Data
public class OfferRequest {
    HotelRequest hotel;

    Long  countryId;

    OfferAvailabilityRequest availability;

    Set<ImageRequest> images;

    String description;
}
