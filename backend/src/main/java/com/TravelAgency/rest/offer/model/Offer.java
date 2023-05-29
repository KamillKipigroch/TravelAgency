package com.TravelAgency.rest.offer.model;

import com.TravelAgency.rest.hotel.model.Hotel;
import com.TravelAgency.rest.image.offer.model.OfferImage;
import com.TravelAgency.rest.offer.offerAvailability.OfferAvailabilityComparator;
import com.TravelAgency.rest.offer.offerAvailability.model.OfferAvailability;
import com.TravelAgency.rest.opinion.model.Opinion;
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
@Table(name = "offer")
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
    @PrimaryKeyJoinColumn
    List<Hotel> hotel;

    @OneToMany(mappedBy = "offer")
    @PrimaryKeyJoinColumn
    List<OfferImage> images;

    @OneToMany(mappedBy = "offer")
    @PrimaryKeyJoinColumn
    List<Opinion> opinions;

    @OneToMany(mappedBy = "offer")
    @PrimaryKeyJoinColumn
    @SortComparator(OfferAvailabilityComparator.class)
    @JsonIgnoreProperties({"offer"})
    List<OfferAvailability> availabilities;

    @Column(length = 3000)
    String description;

    @Column(name = "create_date")
    LocalDateTime createDate;

    Boolean visible;
}
