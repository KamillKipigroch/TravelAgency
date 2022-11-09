package com.TravelAgency.offer.service;

import com.TravelAgency.exception.OfferNoFoundException;
import com.TravelAgency.offer.model.database.OfferDTO;
import com.TravelAgency.offer.model.database.OfferDetailsDTO;
import com.TravelAgency.offer.repository.OfferDetailsRepository;
import com.TravelAgency.offer.repository.OfferRepository;
import com.neovisionaries.i18n.CountryCode;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
@AllArgsConstructor
public class OfferService {
    private final static String OFFER_NO_FOUND = "Failed to find offer with business key ";
    private final OfferRepository offerRepository;
    private final OfferDetailsRepository detailsRepository;

    public OfferDTO addOffer(Offer offerDTO) {
        try {
            offerDTO.setCreateDate(LocalDateTime.now());
            offerDTO.setVisible(true);
            final String createBusinessKeyNumber = String.valueOf(offerRepository.findAll().stream()
                    .filter(or -> Objects.equals(or.getHotelDTO().getRegionDTO().getCountryDTO().getCode(), offerDTO.getHotelDTO().getRegionDTO().getCountryDTO().getCode())
                            && Objects.equals(or.getCreateDate().getYear(), offerDTO.getCreateDate().getYear())
                    ).toList().size() + 1);
            offerDTO.setBusinessKey(
                    CountryCode.findByName(offerDTO.getHotelDTO().getRegionDTO().getCountryDTO().getCode()).get(0).name() + "/"
                            + createBusinessKeyNumber + "/" + offerDTO.getCreateDate().getYear());
        }
        catch (Exception e){
            System.out.println("Error");
        }
        return offerRepository.save(offerDTO);
    }

    public List<OfferDTO> findAllOffer() {
        return offerRepository.findAll().stream().filter(OfferDTO::getVisible).toList();
    }

    public OfferDTO updateOffer(OfferDTO offerDTO) {
        return offerRepository.save(offerDTO);
    }

    public OfferDTO findOfferByBusinessKey(String businessKey) {
        return offerRepository.findAll().stream().findAny()
                .filter(offer -> offer.getBusinessKey() == businessKey)
                .orElseThrow(
                        () -> new OfferNoFoundException(OFFER_NO_FOUND + businessKey));
    }

    public HttpStatus deleteOffer(Long id) {
        Optional<OfferDTO> offer = offerRepository.findById(id);
        if (offer.isPresent()) {
            offerRepository.deleteById(id);
            return HttpStatus.OK;
        }
        return HttpStatus.BAD_REQUEST;
    }
}
