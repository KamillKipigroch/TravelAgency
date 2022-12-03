package com.TravelAgency.rest.model.room;

import com.TravelAgency.rest.model.hotel.Hotel;
import com.TravelAgency.rest.model.roomDetail.RoomDetail;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@NoArgsConstructor
@Data
public class RoomRequest implements Serializable {
    Long roomDetailId;

    String description;

    Double price;

    int quantity;
}
