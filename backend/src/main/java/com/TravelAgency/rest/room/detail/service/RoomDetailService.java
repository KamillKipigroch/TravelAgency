package com.TravelAgency.rest.room.detail.service;

import com.TravelAgency.rest.room.detail.RoomDetailRequest;
import com.TravelAgency.rest.room.detail.model.RoomDetail;
import com.TravelAgency.rest.room.detail.repository.RoomDetailRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.lang.module.FindException;
import java.util.List;

import static com.TravelAgency.comunicates.Communicates.IS_ALREADY_EXIST;
import static com.TravelAgency.comunicates.Communicates.NOT_FOUND_WITH_ID;


@Service
@AllArgsConstructor
public class RoomDetailService {
    private final RoomDetailRepository roomDetailRepository;

    public List<RoomDetail> findAll() {
        return roomDetailRepository.findAll();
    }

    public RoomDetail findById(Long id) {
        return roomDetailRepository.findById(id)
                .orElseThrow(() -> new FindException(NOT_FOUND_WITH_ID + id));
    }

    public RoomDetail add(RoomDetailRequest request) {
        if (roomDetailRepository.findByName(request.getName()).isPresent()) {
            throw new FindException(IS_ALREADY_EXIST + request.getName());
        }
        var newObject = new RoomDetail();
        newObject.setVisible(true);
        newObject.setName(request.getName());
        return roomDetailRepository.save(newObject);
    }

    public RoomDetail update(RoomDetail category) {
        var update = roomDetailRepository.findById(category.getId()).orElseThrow(() ->
                new FindException(NOT_FOUND_WITH_ID + category.getId()));
        update.setName(category.getName());
        update.setVisible(category.getVisible());
        return roomDetailRepository.save(update);
    }

    public void delete(Long id) {
        var object = roomDetailRepository.findById(id).orElseThrow(() ->
                new FindException(NOT_FOUND_WITH_ID + id));
        object.setVisible(false);
        roomDetailRepository.save(object);
        if(roomDetailRepository.findAll().stream().noneMatch(RoomDetail::getVisible)){
            object.setVisible(true);
            roomDetailRepository.save(object);
            throw new IllegalStateException("You cant disable all objects !");
        }
    }
}
