package com.TravelAgency.rest.model.database;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "room")
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
    @JsonIgnoreProperties({"rooms"})
    Hotel hotel;

    @Column(length = 3000)
    String description;

    Double price;

    int quantity;

    Boolean visible;
}
