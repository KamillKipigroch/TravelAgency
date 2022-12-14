package com.TravelAgency.rest.service;

import com.TravelAgency.rest.model.database.Room;
import com.TravelAgency.rest.model.database.RoomImage;
import com.TravelAgency.rest.repository.RoomImageRepository;
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
