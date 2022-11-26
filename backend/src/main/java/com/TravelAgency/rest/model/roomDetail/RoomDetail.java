package com.TravelAgency.rest.model.roomDetail;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "room_details_t")
@NoArgsConstructor
@Getter
@Setter
public class RoomDetail implements Serializable {
    @Id
    @SequenceGenerator(name = "s_room_details", sequenceName = "s_room_details", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "s_room_details")
    @Column(nullable = false, updatable = false)
    Long id;

    @Column(nullable = false)
    String name;

    Boolean visible;

    public RoomDetail(String name) {
        this.name = name;
    }
}
