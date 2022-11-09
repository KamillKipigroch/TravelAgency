package com.TravelAgency.offer.model.database;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table (name = "availability_t")
@NoArgsConstructor
@Getter
@Setter
public class AvailabilityDTO implements Serializable {
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

    @OneToOne
    @JoinColumn(name = "offer_details_id")
    OfferDetailsDTO offer;

    @Column(nullable = false)
    LocalDateTime datetimeStart;

    @Column(nullable = false)
    LocalDateTime datetimeEnd;

    @Column(nullable = false)
    Double price;

    Double promotionPrice;

    Boolean promotion;

    Boolean visible;
}
