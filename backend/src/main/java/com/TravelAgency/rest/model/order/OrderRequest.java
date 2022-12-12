package com.TravelAgency.rest.model.order;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderRequest {
    private Long offerAvailabilityId;
    private Long selectedRoom;
    private String userEmail;
}
