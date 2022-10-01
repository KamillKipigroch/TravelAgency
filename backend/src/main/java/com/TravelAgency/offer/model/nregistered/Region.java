package com.TravelAgency.offer.model.nregistered;

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
public class Region implements Serializable {
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
    @Column(nullable = false, updatable = false)
    String code;

    @ManyToOne
    @JoinColumn(name = "country_id")
    Country country;
    Boolean visible;
    public Region(String code) {
        this.code = code;
        this.visible = true;
    }
}
