package com.TravelAgency.offer.model.database;

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
public class HotelDTO implements Serializable {
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
    OfferDTO offerDTO;

    @ManyToOne
    @JoinColumn(name = "region_id")
    RegionDTO regionDTO;
    int standard;

    String name;

    Double lat;

    Double lng;

    Boolean visible;

    public HotelDTO(OfferDTO offerDTO, RegionDTO regionDTO, int standard, String name, Double lat, Double lng) {
        this.offerDTO = offerDTO;
        this.regionDTO = regionDTO;
        this.standard = standard;
        this.name = name;
        this.lat = lat;
        this.lng = lng;
        this.visible = true;
    }
}
