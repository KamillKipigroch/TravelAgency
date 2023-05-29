package com.TravelAgency.rest.hotel;

import com.TravelAgency.rest.room.RoomRequest;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.util.Set;


@NoArgsConstructor
@Data
public class HotelRequest implements Serializable {

    @Max(5) @Min(0) int standard;

    Set<RoomRequest> rooms;

    String name;

    Double lat;

    Double lng;
}
