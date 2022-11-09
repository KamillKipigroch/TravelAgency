package com.TravelAgency.offer.service;

import com.TravelAgency.offer.model.database.CountryDTO;
import com.TravelAgency.offer.model.database.RegionDTO;
import com.TravelAgency.offer.repository.CountryRepository;
import com.TravelAgency.offer.repository.RegionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CountryService {
    private final static String COUNTRY_NOT_FOUND = "Failed to find county with name ";

    private final CountryRepository countryRepository;
    private final RegionRepository regionRepository;

    public List<RegionDTO> getAllCountries() {
        List<RegionDTO> regionDTOList = regionRepository.findAll();
        return regionDTOList;
    }

    public void deleteCountry(CountryDTO country) throws Exception {
        country.setVisible(false);
        countryRepository.save(country);
    }
}
