package com.TravelAgency.rest.model.dto;

import lombok.Data;

@Data
public class OpinionRequest {
    private String userEmail;

    private Long offerId;

    private Double value;

    private String header;
    private String description;

    Boolean visible;
}
