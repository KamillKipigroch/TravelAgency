package com.TravelAgency.offer.model.database;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "offer_t")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class OfferDTO implements Serializable {
    @Id
    @SequenceGenerator(name = "s_offer",
            sequenceName = "s_offer",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "s_offer"
    )
    @Column(nullable = false, updatable = false)
    Long id;

    @Column(nullable = false, updatable = false, unique = true)
    String businessKey;

    @OneToOne
    @JoinColumn(name = "hotel_id")
    HotelDTO hotelDTO;

    String description;

    @Column(name = "create_date")
    LocalDateTime createDate;

    Boolean visible;


}
