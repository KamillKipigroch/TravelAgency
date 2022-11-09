package com.TravelAgency.offer.model.database;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table (name = "room_t")
@NoArgsConstructor
@Getter
@Setter
public class RoomDTO implements Serializable {
    @Id
    @SequenceGenerator(name = "s_room",
            sequenceName = "s_room",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "s_room"
    )
    @Column(nullable = false, updatable = false)
    Long id;
    @ManyToOne
    @JoinColumn(name = "room_details_id")
    RoomDetailsDTO roomDetailsDTO;

    @ManyToOne
    @JoinColumn(name = "hotel_dto_id")
    HotelDTO hotelDTO;
    Double price;

    int quantity;

    Boolean visible;
}
