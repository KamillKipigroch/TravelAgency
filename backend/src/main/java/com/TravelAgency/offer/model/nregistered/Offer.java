package com.TravelAgency.offer.model.nregistered;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "offer_t")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Offer implements Serializable {
    @Id
    @SequenceGenerator(name = "s_offer",
            sequenceName = "s_offer",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "s_offer"
    )
    @Column(nullable = false, updatable = false)
    Long id;
    String businessKey;
    @OneToOne
    @JoinColumn(name = "hotel_id")
    Hotel hotel;
    String description;
    Boolean visible;

}
