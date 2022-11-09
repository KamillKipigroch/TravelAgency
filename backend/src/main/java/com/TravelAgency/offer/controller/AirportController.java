package com.TravelAgency.offer.controller;

import com.TravelAgency.offer.model.database.AirportDTO;
import com.TravelAgency.offer.model.database.AirportDetailsDTO;
import com.TravelAgency.offer.model.database.OfferDetailsDTO;
import com.TravelAgency.offer.service.AirportService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("airport")
public class AirportController {
    private final AirportService airportService;
    private final AirportDetailsService airportDetailsService;

    @GetMapping("/all-name")
    public ResponseEntity<List<AirportDetailsDTO>> getAllAirportName() {
        List<AirportDetailsDTO> listAirportName = airportDetailsService.findAllAirport();
        return new ResponseEntity<>(listAirportName, HttpStatus.OK);
    }

    @PostMapping("/create-airport")
    public ResponseEntity<List<AirportDTO>> createAirports(@RequestBody List<AirportDTO> airportDTOS, OfferDetailsDTO offer) {
        List<AirportDTO> newAirportDTOS = airportService.addAirports(airportDTOS, offer);
        return new ResponseEntity<>(newAirportDTOS, HttpStatus.OK);
    }

    @PutMapping("/edit-airport")
    public ResponseEntity<List<AirportDTO>> updateAirports(@RequestBody List<AirportDTO> airportDTOS, OfferDetailsDTO offer) {
        List<AirportDTO> newAirportDTOS = airportService.updateAirports(airportDTOS);
        return new ResponseEntity<>(newAirportDTOS, HttpStatus.OK);
    }

    @PutMapping("/delete")
    public void deleteAirport(@RequestBody AirportDTO airportDTO) {
        airportService.deleteAirport(airportDTO);
    }
}
