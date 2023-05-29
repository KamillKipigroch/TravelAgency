package com.TravelAgency.rest.order.status;

import lombok.Data;

@Data
public class OrderStatusRequest {
    String name;
    Boolean visible;
    Integer level;
}
