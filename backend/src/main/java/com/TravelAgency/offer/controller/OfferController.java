package com.TravelAgency.offer.controller;

import com.TravelAgency.offer.model.database.OfferDTO;
import com.TravelAgency.offer.service.OfferService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("offer")
public class OfferController {
    private final OfferService offerService;

    @GetMapping("/all")
    public ResponseEntity<List<OfferDTO>> getAllOffers() {
        List<OfferDTO> listOfferDTOS = offerService.findAllOffer();
        return new ResponseEntity<>(listOfferDTOS, HttpStatus.OK);
    }

    @GetMapping("/find/{business-key}")
    public ResponseEntity<OfferDTO> getOfferByBusinessKey(@PathVariable("business-key") String businessKey) {
        OfferDTO offerDTO = offerService.findOfferByBusinessKey(businessKey);
        return new ResponseEntity<>(offerDTO, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<OfferDTO> createOffer(@RequestBody OfferDTO offerDTO) {
        OfferDTO newOfferDTO = offerService.addOffer(offerDTO);
        return new ResponseEntity<>(newOfferDTO, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<OfferDTO> updateOffer(@RequestBody OfferDTO offerDTO) {
        OfferDTO updateOfferDTO = offerService.updateOffer(offerDTO);
        return new ResponseEntity<>(updateOfferDTO, HttpStatus.OK);
    }

    @PutMapping("/delete/{business-key}")
    public ResponseEntity<OfferDTO> deleteOffer(@RequestBody String businessKey) {
        OfferDTO updateOfferDTO = offerService.findOfferByBusinessKey(businessKey);
        updateOfferDTO.setVisible(false);
        offerService.updateOffer(updateOfferDTO);
        return new ResponseEntity<>(updateOfferDTO, HttpStatus.OK);
    }
}
