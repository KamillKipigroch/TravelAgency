package com.TravelAgency.offer.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table (name = "offer")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Offer implements Serializable {
    @Id
    @SequenceGenerator(name = "offer_sequence",
            sequenceName = "offer_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "offer_sequence"
    )
    @Column(nullable = false, updatable = false)
    Long id;
    @Column(nullable = false, updatable = false)
    String businessKey;
    Double price;
    String country;
    String imageUrl;
    LocalDateTime createDate;

    public Offer(String businessKey, Double price, String country, String imageUrl) {
        this.businessKey = businessKey;
        this.price = price;
        this.country = country;
        this.imageUrl = imageUrl;
    }
}
