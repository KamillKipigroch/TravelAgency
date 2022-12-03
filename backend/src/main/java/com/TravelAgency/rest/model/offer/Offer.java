package com.TravelAgency.rest.model.offer;

import com.TravelAgency.rest.model.country.Country;
import com.TravelAgency.rest.model.hotel.Hotel;
import com.TravelAgency.rest.model.offerAvailability.OfferAvailability;
import com.TravelAgency.rest.model.offerImage.OfferImage;
import com.TravelAgency.rest.model.opinion.Opinion;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "offer_t")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Offer implements Serializable {
    @Id
    @SequenceGenerator(name = "s_offer", sequenceName = "s_offer", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "s_offer")
    @Column(nullable = false, updatable = false)
    Long id;

    @OneToMany(mappedBy = "offer")
    Set<Hotel> hotel;

    @OneToOne
    @JoinColumn(name = "country_id")
    Country country;

    @OneToMany(mappedBy = "offer")
    Set<OfferImage> images;

    @OneToMany(mappedBy = "offer")
    Set<Opinion> opinions;

    @OneToMany(mappedBy = "offer")
    Set<OfferAvailability> availabilities = new HashSet<>();

    String description;

    @Column(name = "create_date")
    LocalDateTime createDate;

    Boolean visible;


}
