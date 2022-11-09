package com.TravelAgency.offer.service;

import com.TravelAgency.offer.model.Airport;
import com.TravelAgency.offer.model.database.AirportDTO;
import com.TravelAgency.offer.model.database.AirportDetailsDTO;
import com.TravelAgency.offer.model.database.OfferDetailsDTO;
import com.TravelAgency.offer.repository.AirportDetailsRepository;
import com.TravelAgency.offer.repository.AirportRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.lang.module.FindException;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class AirportService {
    private final static String AIRPORT_NO_FOUND = "Failed to find airport with ";
    private final AirportRepository airportRepository;
    private final AirportDetailsRepository airportDetailsRepository;

    public List<Airport> findAllAirport() {
        List<Airport> airports = new ArrayList<Airport>();
        List<AirportDetailsDTO> airportList = airportDetailsRepository.findAll();

        airportList.forEach(airportDetailsDTO -> {
            airports.add(new Airport(airportDetailsDTO.getCode(), 0.0, ""));
        });
        return airports;
    }

    public Airport addAirport(Airport airport) {
        AirportDTO airportDTO = new AirportDTO(airport.getPrice(), airport.getBusinessKey());
        airportDTO.setAirportDetailsDTO(
                airportDetailsRepository.findByCode(airport.getName()).orElseThrow(() -> {
                    return new FindException(AIRPORT_NO_FOUND + " name " + airport.getName());
                })
        );
        airportRepository.save(airportDTO);
        return airport;
    }

    public void deleteAirport(Airport airport) {
        AirportDTO airportDTO = airportRepository.findByBusinessKey(airport.getBusinessKey()).orElseThrow(() -> {
            return new FindException(AIRPORT_NO_FOUND + " business key " + airport.getBusinessKey());
        });
        airportDTO.setVisible(false);
        airportRepository.save(airportDTO);
    }
}
