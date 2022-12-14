package com.TravelAgency.rest.controller;

import com.TravelAgency.rest.model.database.Country;
import com.TravelAgency.rest.service.CountryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/country")
public class CountryController {
    private final CountryService countryService;

    @GetMapping("/get-all")
    public ResponseEntity<List<Country>> getAll() {
        var categories = countryService.findAll();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }
}
