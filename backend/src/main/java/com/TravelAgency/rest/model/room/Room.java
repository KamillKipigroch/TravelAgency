package com.TravelAgency.rest.model.room;

import com.TravelAgency.rest.model.roomDetail.RoomDetail;
import com.TravelAgency.rest.model.hotel.Hotel;
import com.TravelAgency.rest.model.roomImage.RoomImage;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "room_t")
@NoArgsConstructor
@Getter
@Setter
public class Room implements Serializable {
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
    RoomDetail roomDetail;

    @OneToMany(mappedBy = "room")
    Set<RoomImage> roomImage;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    @JsonIgnore
    Hotel hotel;

    String description;

    Double price;

    int quantity;

    Boolean visible;
}
