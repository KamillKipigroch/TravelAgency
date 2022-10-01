package com.TravelAgency.offer.model.nregistered;

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
public class Airport implements Serializable {
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
    OfferDetails offer;

    @Column(nullable = false, unique = true)
    String code;

    Boolean visible;
}
