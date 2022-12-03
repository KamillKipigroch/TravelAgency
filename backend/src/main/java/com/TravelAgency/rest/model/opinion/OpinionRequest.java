package com.TravelAgency.rest.model.opinion;

import lombok.Data;

@Data
public class OpinionRequest {
    private String userEmail;

    private Long offerId;

    private Double value;

    private String description;

    Boolean visible;
}
