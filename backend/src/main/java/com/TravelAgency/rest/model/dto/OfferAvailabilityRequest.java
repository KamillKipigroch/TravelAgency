package com.TravelAgency.rest.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class OfferAvailabilityRequest {
    LocalDateTime datetimeStart;

    LocalDateTime datetimeEnd;

    Double price;

    Double promotionPrice;

    Boolean promotion;
}
