package com.TravelAgency.offer.model;

import com.TravelAgency.offer.model.database.RegionDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Hotel {
    RegionDTO regionDTO;
    int standard;
    String name;
    Double lat;
    Double lng;
    List<Room> roomList;
}
