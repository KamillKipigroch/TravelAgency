package com.TravelAgency.rest.model.offerAvailability;

import com.TravelAgency.rest.model.offer.Offer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table (name = "availability_t")
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
    @JsonIgnore
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
