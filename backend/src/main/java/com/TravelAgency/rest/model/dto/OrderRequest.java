package com.TravelAgency.rest.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {
    private Long offerAvailabilityId;
    private Long selectedRoom;
    private String userEmail;
}
