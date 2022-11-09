package com.TravelAgency.offer.model.database;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "offer_details_t")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class OfferDetailsDTO implements Serializable {
    @Id
    @SequenceGenerator(name = "s_offer_detail",
            sequenceName = "s_offer_detail",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "s_offer_detail"
    )
    @Column(nullable = false, updatable = false)
    Long id;

    @OneToOne
    @JoinColumn(name = "hotel_id")
    HotelDTO hotelDTO;

    @ManyToOne
    @JoinColumn(name = "offer_id")
    OfferDTO offerDTO;

    Boolean visible;
}
