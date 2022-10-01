package com.TravelAgency.offer.model.nregistered;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table (name = "country_t")
@NoArgsConstructor
@Getter
@Setter
public class Country implements Serializable {
    @Id
    @SequenceGenerator(name = "s_country",
            sequenceName = "s_country",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "s_country"
    )
    @Column(nullable = false, updatable = false)
    Long id;
    @Column(nullable = false, updatable = false, unique = true)
    String code;
    Boolean visible;

    public Country(String code) {
        this.code = code;
        this.visible = true;
    }
}
