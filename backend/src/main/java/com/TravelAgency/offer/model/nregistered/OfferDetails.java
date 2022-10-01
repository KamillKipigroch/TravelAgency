package com.TravelAgency.offer.model.nregistered;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "offer_details_t")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class OfferDetails implements Serializable {
    @Id
    @SequenceGenerator(name = "s_offer_detail",
            sequenceName = "s_offer_detail",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "s_offer_detail"
    )
    @Column(nullable = false, updatable = false)
    Long id;
    @Column(nullable = false, updatable = false, unique = true)
    String businessKey;
    @OneToOne
    @JoinColumn(name = "hotel_id")
    Hotel hotel;
    LocalDateTime createDate;
    Boolean visible;
}
