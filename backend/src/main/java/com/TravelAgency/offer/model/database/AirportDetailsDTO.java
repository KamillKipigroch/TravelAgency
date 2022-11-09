package com.TravelAgency.offer.model.database;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table (name = "air_port_details_t")
@NoArgsConstructor
@Getter
@Setter
public class AirportDetailsDTO implements Serializable {
    @Id
    @SequenceGenerator(name = "s_air_port_details",
            sequenceName = "s_air_port_details",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "s_air_port_details"
    )
    @Column(nullable = false, updatable = false)
    Long id;
    @Column(nullable = false, unique = true)
    String code;

    Boolean visible;

    public AirportDetailsDTO(String code) {
        this.code = code;
        this.visible = true;
    }
}
