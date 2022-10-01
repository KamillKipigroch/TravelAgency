package com.TravelAgency.offer.service;

import com.TravelAgency.exception.OfferNoFoundException;
import com.TravelAgency.offer.model.nregistered.Country;
import com.TravelAgency.offer.repository.CountryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class CountryService {
    private final static String COUNTRY_NO_FOUND = "Failed to find country with name ";
    private final CountryRepository countryRepository;

    public Country addCountry(Country country) {
        country.setVisible(true);
        return countryRepository.save(country);
    }

    public List<Country> findAllOffer() {
        return countryRepository.findAll();
    }

    public Country findCountryByName(String name) {
        return countryRepository.findAll().stream().findAny()
                .filter(country -> Objects.equals(country.getCode(), name))
                .orElseThrow(
                        () -> new OfferNoFoundException(COUNTRY_NO_FOUND + name));
    }

    public void deleteCountry(Country country) {
        country.setVisible(false);
         countryRepository.save(country);
    }
}
