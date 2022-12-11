package com.TravelAgency.rest.model.roomImage;

import com.TravelAgency.rest.model.room.Room;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "room_image_t")
@NoArgsConstructor
@Getter
@Setter
public class RoomImage implements Serializable {
    @Id
    @SequenceGenerator(name = "s_room_image",
            sequenceName = "s_room_image",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "s_room_image"
    )
    @Column(nullable = false, updatable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "room_id")
    @JsonIgnore
    private Room room;

    private String name;

    private String type;

    @Lob
    @Column(name = "imageData",length = 5000)
    private byte[] imageData;

    private Boolean visible;
}
