package com.TravelAgency.rest.offer.offerAvailability;

import com.TravelAgency.rest.offer.model.Offer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table (name = "availability")
@NoArgsConstructor
@Getter
@Setter
public class OfferAvailability implements Serializable {
    @Id
    @SequenceGenerator(name = "s_availability",
            sequenceName = "s_availability",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "s_availability"
    )
    @Column(nullable = false, updatable = false)
    Long id;

    @ManyToOne
    @JoinColumn(name = "offer_id")
    @PrimaryKeyJoinColumn
    Offer offer;

    @Column(nullable = false)
    LocalDate datetimeStart;

    @Column(nullable = false)
    LocalDate datetimeEnd;

    @Column(nullable = false)
    Double price;

    Double promotionPrice;

    Boolean promotion;

    Boolean visible;
}
