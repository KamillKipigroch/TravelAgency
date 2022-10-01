package com.TravelAgency.offer.model.nregistered;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table (name = "hotel_t")
@NoArgsConstructor
@Getter
@Setter
public class Hotel implements Serializable {
    @Id
    @SequenceGenerator(name = "s_hotel",
            sequenceName = "s_hotel",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "s_hotel"
    )
    @Column(nullable = false, updatable = false)
    Long id;
    @OneToOne
    @JoinColumn(name = "offer_id")
    Offer offer;
    @ManyToOne
    @JoinColumn(name = "region_id")
    Region region;
    Boolean visible;
}
