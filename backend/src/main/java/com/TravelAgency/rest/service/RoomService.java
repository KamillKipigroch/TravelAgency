package com.TravelAgency.rest.service;

import com.TravelAgency.rest.model.hotel.Hotel;
import com.TravelAgency.rest.model.room.Room;
import com.TravelAgency.rest.model.room.RoomRequest;
import com.TravelAgency.rest.model.roomDetail.RoomDetail;
import com.TravelAgency.rest.repository.RoomRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.lang.module.FindException;

import static com.TravelAgency.comunicates.Communicates.NOT_FOUND_WITH_ID;


@Service
@AllArgsConstructor
public class RoomService {
    private final RoomRepository roomRepository;

    public Room findById(Long id) {
        return roomRepository.findById(id)
                .orElseThrow(() -> new FindException(NOT_FOUND_WITH_ID + id));
    }

    public Room add(Room roomRequest, Hotel hotel){
        var newObject = new Room();
        newObject.setHotel(hotel);
        newObject.setRoomDetail(roomRequest.getRoomDetail());
        newObject.setPrice(roomRequest.getPrice());
        newObject.setDescription(roomRequest.getDescription());
        newObject.setQuantity(roomRequest.getQuantity());
        newObject.setVisible(true);
        return roomRepository.save(newObject);
    }
}
