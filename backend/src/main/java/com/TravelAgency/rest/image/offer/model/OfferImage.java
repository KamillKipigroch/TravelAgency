package com.TravelAgency.rest.image.offer.model;

import com.TravelAgency.rest.offer.model.Offer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "offer_image")
@NoArgsConstructor
@Getter
@Setter
public class OfferImage implements Serializable {
    @Id
    @SequenceGenerator(name = "s_offer_image",
            sequenceName = "s_offer_image",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "s_offer_image"
    )
    @Column(nullable = false, updatable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "offer_id")
    @JsonIgnore
    @PrimaryKeyJoinColumn
    private Offer offer;

    private String name;

    private Boolean visible;

    public OfferImage(String name, Offer offer) {
        this.name = name;
        this.offer= offer;
        this.visible = true;
    }
}
