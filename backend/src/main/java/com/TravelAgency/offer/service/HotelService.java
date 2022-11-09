package com.TravelAgency.offer.service;

import com.TravelAgency.offer.model.Hotel;
import com.TravelAgency.offer.model.Room;
import com.TravelAgency.offer.model.database.AirportDetailsDTO;
import com.TravelAgency.offer.model.database.HotelDTO;
import com.TravelAgency.offer.model.database.RoomDTO;
import com.TravelAgency.offer.repository.HotelRepository;
import com.TravelAgency.offer.repository.RoomRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.lang.module.FindException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class HotelService {
    private final static String HOTEL_NO_FOUND = "Failed to find airport with name ";
    private final HotelRepository hotelRepository;
    private final RoomRepository roomRepository;

    public Hotel addHotel(Hotel hotel) {
        HotelDTO hotelDTO = hotelRepository.findByName(hotel.getName()).orElseThrow(() -> {
            return new FindException(HOTEL_NO_FOUND + hotel.getName());
        });

        hotelDTO.setVisible(true);
        hotelRepository.save(hotelDTO);
        return hotel;
    }

    public List<Hotel> findAllHotel() {
        List<HotelDTO> hotelDTOList = hotelRepository.findAll();
        List<RoomDTO> roomDTOList = roomRepository.findAll();
        List<Hotel> hotels = new ArrayList<Hotel>();

        hotelDTOList.stream().filter(HotelDTO::getVisible).forEach(it -> {
        });

        hotelDTOList.forEach(hotel -> {
            List<Room> roomList = new ArrayList<Room>();
            roomDTOList.stream().filter(it -> {
                return Objects.equals(it.getHotelDTO().getName(), hotel.getName()) && it.getQuantity() > 0;
            }).forEach(room -> roomList.add(new Room(room.getRoomDetailsDTO().getCode(), room.getPrice())));
            hotels.add(new Hotel(hotel.getRegionDTO(), hotel.getStandard(), hotel.getName(), hotel.getLat(), hotel.getLng(), roomList));
        });
        return hotels;
    }

    public void deleteHotel(Hotel hotel) {
        hotelRepository.findByName(hotel.getName()).orElseThrow(() -> new FindException(HOTEL_NO_FOUND + hotel.getName())).setVisible(false);

    }
}
