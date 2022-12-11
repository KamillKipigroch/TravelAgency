package com.TravelAgency.rest.model.room;

import com.TravelAgency.rest.model.ImageRequest;
import com.TravelAgency.rest.model.hotel.Hotel;
import com.TravelAgency.rest.model.roomDetail.RoomDetail;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@Data
public class RoomRequest implements Serializable {
    Long roomDetailId;

    List<ImageRequest> images;

    String description;

    Double price;

    int quantity;
}
