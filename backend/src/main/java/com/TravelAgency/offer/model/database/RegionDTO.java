package com.TravelAgency.offer.model.database;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table (name = "region_t")
@NoArgsConstructor
@Getter
@Setter
public class RegionDTO implements Serializable {
    @Id
    @SequenceGenerator(name = "s_region",
            sequenceName = "s_region",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "s_region"
    )
    @Column(nullable = false, updatable = false)
    Long id;

    @ManyToOne
    @JoinColumn(name = "country_id")
    CountryDTO countryDTO;

    String code;

    Boolean visible;

    public RegionDTO(CountryDTO country, String code) {
        this.countryDTO = country;
        this.code = code;
        visible = true;
    }
}
