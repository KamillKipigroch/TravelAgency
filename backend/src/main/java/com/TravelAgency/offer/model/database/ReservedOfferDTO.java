package com.TravelAgency.offer.model.database;

import com.TravelAgency.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "offer_reserved_t")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ReservedOfferDTO implements Serializable {
    @Id
    @SequenceGenerator(name = "s_offer_reserved",
            sequenceName = "s_offer_reserved",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "s_offer_reserved"
    )
    @Column(nullable = false, updatable = false)
    Long id;

    @ManyToOne
    @JoinColumn(name = "offer_id")
    OfferDTO offerDTO;
    @OneToOne
    @JoinColumn(name = "hotel_id")
    HotelDTO hotelDTO;

    @ManyToOne
    @JoinColumn(name = "room_id")
    RoomDTO roomDTO;

    @ManyToOne
    @JoinColumn(name = "airport_id")
    AirportDTO airportDTO;

    @ManyToOne
    @JoinColumn(name = "availability_id")
    AvailabilityDTO availabilityDTO;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;

    int quantity;

    Float price;

    @Column(name = "create_date")
    LocalDateTime createDate;

    Boolean visible;

    public ReservedOfferDTO(HotelDTO hotelDTO, String description) {
        this.hotelDTO = hotelDTO;
    }
}
