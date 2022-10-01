package com.TravelAgency.offer.controller;

import com.TravelAgency.offer.model.nregistered.Offer;
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
    public ResponseEntity<List<Offer>> getAllOffers() {
        List<Offer> listOffers = offerService.findAllOffer();
        return new ResponseEntity<>(listOffers, HttpStatus.OK);
    }

    @GetMapping("/find/{businesskey}")
    public ResponseEntity<Offer> getOfferByBusinessKey(@PathVariable("businesskey") String businessKey) {
        Offer offer = offerService.findOfferByBusinessKey(businessKey);
        return new ResponseEntity<>(offer, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Offer> createOffer(@RequestBody Offer offer) {
        Offer newOffer = offerService.addOffer(offer);
        return new ResponseEntity<>(newOffer, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Offer> updateOffer(@RequestBody Offer offer) {
        Offer updateOffer = offerService.updateOffer(offer);
        return new ResponseEntity<>(updateOffer, HttpStatus.OK);
    }

    @PutMapping("/delete/{businesskey}")
    public ResponseEntity<Offer> deleteOffer(@RequestBody String businessKey) {
        Offer updateOffer = offerService.findOfferByBusinessKey(businessKey);
        updateOffer.setVisible(false);
        offerService.updateOffer(updateOffer);
        return new ResponseEntity<>(updateOffer, HttpStatus.OK);
    }
}
