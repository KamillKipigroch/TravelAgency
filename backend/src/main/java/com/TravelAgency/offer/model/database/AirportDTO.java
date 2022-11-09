package com.TravelAgency.offer.model.database;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table (name = "air_port_t")
@NoArgsConstructor
@Getter
@Setter
public class AirportDTO implements Serializable {
    @Id
    @SequenceGenerator(name = "s_air_port",
            sequenceName = "s_air_port",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "s_air_port"
    )
    @Column(nullable = false, updatable = false)
    Long id;

    @OneToOne
    @JoinColumn(name = "offer_details_id")
    OfferDetailsDTO offerDetailsDTO;

    @ManyToOne
    @JoinColumn(name = "airport_details_id")
    AirportDetailsDTO airportDetailsDTO;

    @Column(nullable = false, unique = true)
    String businessKey;

    Double price;

    Boolean visible;

    public  AirportDTO (Double price, String businessKey){
        this.price = price;
        this.businessKey = businessKey;
        this.visible = true;
    }
}
