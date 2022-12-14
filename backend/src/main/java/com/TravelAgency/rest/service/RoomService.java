package com.TravelAgency.rest.service;

import com.TravelAgency.rest.model.database.Hotel;
import com.TravelAgency.rest.model.database.Room;
import com.TravelAgency.rest.repository.RoomRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.lang.module.FindException;
import java.util.List;

import static com.TravelAgency.comunicates.Communicates.NOT_FOUND_WITH_ID;


@Service
@AllArgsConstructor
public class RoomService {
    private final RoomRepository roomRepository;

    public Room findById(Long id) {
        return roomRepository.findById(id)
                .orElseThrow(() -> new FindException(NOT_FOUND_WITH_ID + id));
    }

    public List<Room> findByHotel(Long hotel) {
        return roomRepository.findByHotelId(hotel)
                .orElseThrow(() -> new FindException(NOT_FOUND_WITH_ID + hotel));
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
