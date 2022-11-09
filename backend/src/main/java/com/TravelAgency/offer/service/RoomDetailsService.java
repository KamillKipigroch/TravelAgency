package com.TravelAgency.offer.service;

import com.TravelAgency.offer.model.Room;
import com.TravelAgency.offer.model.database.AirportDetailsDTO;
import com.TravelAgency.offer.model.database.RoomDTO;
import com.TravelAgency.offer.model.database.RoomDetailsDTO;
import com.TravelAgency.offer.repository.AirportDetailsRepository;
import com.TravelAgency.offer.repository.RoomDetailRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.lang.module.FindException;
import java.util.List;

@Service
@AllArgsConstructor
public class RoomDetailsService {
    private final static String ROOM_DETAILS_NO_FOUND = "Failed to find detail with name ";
    private final RoomDetailRepository roomDetailRepository;

    public RoomDetailsDTO addRoomDetails(Room room) {
        RoomDetailsDTO roomDTO = new RoomDetailsDTO(room.getName());
        roomDTO.setVisible(true);
        return roomDetailRepository.save(roomDTO);
    }

    public List<RoomDetailsDTO> findAllRoomDetails() {
        return roomDetailRepository.findAll();
    }

    public void deleteRoomDetails(Room room) {
        RoomDetailsDTO roomDetailsDTO = roomDetailRepository.findByCode(room.getName()).orElseThrow(() -> {
            return new FindException(ROOM_DETAILS_NO_FOUND + room.getName());
        });
        roomDetailsDTO.setVisible(false);
        roomDetailRepository.save(roomDetailsDTO);
}
}
