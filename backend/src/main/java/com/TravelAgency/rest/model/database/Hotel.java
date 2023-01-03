package com.TravelAgency.rest.model.database;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "hotel_t")
@NoArgsConstructor
@Getter
@Setter
public class Hotel implements Serializable {
    @Id
    @SequenceGenerator(name = "s_hotel", sequenceName = "s_hotel", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "s_hotel")
    @Column(nullable = false, updatable = false)
    @JsonIgnore
    Long id;

    @OneToOne
    @JoinColumn(name = "country_id")
    Country country;

    @ManyToOne
    @JoinColumn(name = "offer_id")
    @JsonIgnore
    Offer offer;

    @Max(5) @Min(0) int standard;

    @OneToMany(mappedBy = "hotel")
    @JsonIgnoreProperties({"hotel"})
    Set<Room> rooms;
    String name;
    Boolean visible;

    public Hotel(Offer offer, int standard, String name) {
        this.offer = offer;
        this.standard = standard;
        this.name = name;
        this.visible = true;
    }
}
