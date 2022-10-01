package com.TravelAgency.offer.controller;

import com.TravelAgency.offer.model.nregistered.Country;
import com.TravelAgency.offer.model.nregistered.Offer;
import com.TravelAgency.offer.service.CountryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("board-basic")
public class BoardBasisController {
    private final CountryService countryService;

    @GetMapping("/all")
    public ResponseEntity<List<Country>> getAllCountry() {
        List<Country> listCountry = countryService.findAllOffer();
        return new ResponseEntity<>(listCountry, HttpStatus.OK);
    }

    @GetMapping("/find/{name}")
    public ResponseEntity<Country> getCountryByName(@PathVariable("name") String name) {
        Country country = countryService.findCountryByName(name);
        return new ResponseEntity<>(country, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Country> createCountry(@RequestBody Country country) {
        Country newCountry = countryService.addCountry(country);
        return new ResponseEntity<>(newCountry, HttpStatus.OK);
    }

    @PutMapping("/delete")
    public ResponseEntity<Offer> deleteCountry(@RequestBody String name) {
        countryService.deleteCountry(countryService.findCountryByName(name));
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
