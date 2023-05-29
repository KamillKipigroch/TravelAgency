package com.TravelAgency.rest.room;

import com.TravelAgency.rest.image.ImageRequest;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@Data
public class RoomRequest implements Serializable {
    Long roomDetailId;

    List<ImageRequest> images;

    String description;

    Double price;

    int quantity;
}
