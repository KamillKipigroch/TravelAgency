package com.TravelAgency.rest.image.room.service;

import com.TravelAgency.rest.image.room.model.RoomImage;
import com.TravelAgency.rest.image.room.repository.RoomImageRepository;
import com.TravelAgency.rest.room.model.Room;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RoomImageService {
    private final RoomImageRepository roomImageRepository;

    public RoomImage add(Room room, String image) {
        var newObject = new RoomImage();
        newObject.setVisible(true);
        newObject.setRoom(room);
        newObject.setName(image);
        return roomImageRepository.save(newObject);
    }
}
