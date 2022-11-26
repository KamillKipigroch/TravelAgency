package com.TravelAgency.rest.model.offer;

import com.TravelAgency.rest.model.hotel.Hotel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

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

    @Column(nullable = false, updatable = false)
    String offerCode;

    @OneToOne
    @JoinColumn(name = "hotel_id")
    Hotel hotel;

    String description;

    @Column(name = "create_date")
    LocalDateTime createDate;

    Boolean visible;


}
