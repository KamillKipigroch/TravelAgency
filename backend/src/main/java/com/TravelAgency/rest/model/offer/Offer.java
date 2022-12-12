package com.TravelAgency.rest.model.offer;

import com.TravelAgency.rest.model.country.Country;
import com.TravelAgency.rest.model.hotel.Hotel;
import com.TravelAgency.rest.model.offerAvailability.OfferAvailability;
import com.TravelAgency.rest.model.offerAvailability.OfferAvailabilityComparator;
import com.TravelAgency.rest.model.offerImage.OfferImage;
import com.TravelAgency.rest.model.opinion.Opinion;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SortComparator;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "offer_t")
@NoArgsConstructor
@Getter
@Setter
public class Offer implements Serializable {
    @Id
    @SequenceGenerator(name = "s_offer", sequenceName = "s_offer", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "s_offer")
    @Column(nullable = false, updatable = false)
    Long id;

    @OneToMany(mappedBy = "offer")
    List<Hotel> hotel;

    @OneToOne
    @JoinColumn(name = "country_id")
    Country country;

    @OneToMany(mappedBy = "offer")
    List<OfferImage> images;

    @OneToMany(mappedBy = "offer")
    List<Opinion> opinions;

    @OneToMany(mappedBy = "offer")
    @SortComparator(OfferAvailabilityComparator.class)
    @JsonIgnoreProperties({"offer"})
    List<OfferAvailability> availabilities;

    String description;

    @Column(name = "create_date")
    LocalDateTime createDate;

    Boolean visible;
}
