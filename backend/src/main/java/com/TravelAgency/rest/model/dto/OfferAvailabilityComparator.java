package com.TravelAgency.rest.model.dto;

import com.TravelAgency.rest.model.database.OfferAvailability;

import java.util.Comparator;


public class OfferAvailabilityComparator implements Comparator<OfferAvailability> {

    @Override
    public int compare(OfferAvailability o1, OfferAvailability o2) {
        return o1.datetimeStart.compareTo(o2.getDatetimeStart());
    }
}
