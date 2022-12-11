package com.TravelAgency.rest.service;

import com.TravelAgency.rest.model.ImageRequest;
import com.TravelAgency.rest.model.room.Room;
import com.TravelAgency.rest.model.roomImage.RoomImage;
import com.TravelAgency.rest.repository.RoomImageRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RoomImageService {
    private final RoomImageRepository roomImageRepository;

    public RoomImage add(Room room, ImageRequest image) {
        var newObject = new RoomImage();
        newObject.setVisible(true);
        newObject.setRoom(room);
        newObject.setName(image.getUrl());
        return roomImageRepository.save(newObject);
    }
}
