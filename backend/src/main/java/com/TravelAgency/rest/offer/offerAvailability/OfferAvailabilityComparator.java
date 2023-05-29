package com.TravelAgency.rest.offer.offerAvailability;

import com.TravelAgency.rest.offer.offerAvailability.model.OfferAvailability;

import java.util.Comparator;

public class OfferAvailabilityComparator implements Comparator<OfferAvailability> {

    @Override
    public int compare(OfferAvailability o1, OfferAvailability o2) {
        return o1.getDatetimeStart().compareTo(o2.getDatetimeStart());
    }
}
