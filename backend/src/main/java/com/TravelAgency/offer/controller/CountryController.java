package com.TravelAgency.offer.controller;

import com.TravelAgency.offer.model.Country;
import com.TravelAgency.offer.model.database.CountryDTO;
import com.TravelAgency.offer.model.database.OfferDTO;
import com.TravelAgency.offer.model.database.RegionDTO;
import com.TravelAgency.offer.service.CountryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("country")
public class CountryController {
    private final CountryService countryService;

    @GetMapping("/all")
    public ResponseEntity<List<RegionDTO>> getAllRegions() {
        List<RegionDTO> listCountry = countryService.getAllCountries();
        return new ResponseEntity<>(listCountry, HttpStatus.OK);
    }

    @PutMapping("/delete")
    public ResponseEntity<OfferDTO> deleteCountry(@RequestBody CountryDTO country) throws Exception {
        countryService.deleteCountry(country);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
