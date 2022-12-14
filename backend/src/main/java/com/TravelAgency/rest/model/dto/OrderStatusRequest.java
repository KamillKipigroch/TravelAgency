package com.TravelAgency.rest.model.dto;

import lombok.Data;

@Data
public class OrderStatusRequest {
    String name;
    Boolean visible;
    Integer level;
}
