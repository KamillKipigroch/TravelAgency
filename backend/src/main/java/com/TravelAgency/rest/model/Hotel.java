package com.TravelAgency.rest.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
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
    Long id;

    @OneToOne
    @JoinColumn(name = "offer_id")
    Offer offer;

    int standard;

    @OneToMany
    @JoinColumn(name = "image_id")
    Set<Image> images;


    @OneToMany
    @JoinColumn(name = "room_id")
    Set<Room> rooms;
    String name;

    Double lat;

    Double lng;

    Boolean visible;

    public Hotel(Offer offer, int standard, String name, Double lat, Double lng) {
        this.offer = offer;
        this.standard = standard;
        this.name = name;
        this.lat = lat;
        this.lng = lng;
        this.visible = true;
    }
}
