package com.TravelAgency.rest.offer.controller;

import com.TravelAgency.rest.country.service.CountryService;
import com.TravelAgency.rest.hotel.service.HotelService;
import com.TravelAgency.rest.offer.model.Offer;
import com.TravelAgency.rest.offer.offerAvailability.model.OfferAvailability;
import com.TravelAgency.rest.offer.offerAvailability.service.OfferAvailabilityService;
import com.TravelAgency.rest.offer.service.OfferService;
import com.TravelAgency.rest.opinion.model.Opinion;
import com.TravelAgency.rest.room.service.RoomService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.module.FindException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import static com.TravelAgency.config.SwaggerConfig.BEARER_KEY_SECURITY_SCHEME;

@RestController
@AllArgsConstructor
@RequestMapping("/api/offer")
public class OfferController {
    private final OfferService offerService;
    private final CountryService countryService;
    private final HotelService hotelService;
    private final OfferAvailabilityService offerAvailabilityService;
    private final RoomService roomService;

    @GetMapping("/get-all")
    public ResponseEntity<List<Offer>> getAll() {
        var offers = offerService.findAll();
        return new ResponseEntity<>(offers, HttpStatus.OK);
    }

    @GetMapping("/get-recommended")
    public ResponseEntity<List<Offer>> getRecommender() {
        var offers = offerService.findAll();
        offers = getRecommended(offers);

        return new ResponseEntity<>(offers, HttpStatus.OK);
    }
    @GetMapping("/get-last-minute")
    public ResponseEntity<List<Offer>> getLastMinute() {
        var offers = offerService.findAll();

        offers.forEach(offer -> offer.setAvailabilities(offer.getAvailabilities().stream().filter(OfferAvailability::getPromotion).toList()));
        offers = offers.stream().filter(offer -> !offer.getAvailabilities().isEmpty()).toList();
        offers = getRecommended(offers);

        return new ResponseEntity<>(offers, HttpStatus.OK);
    }

    @Operation(security = {})
    @RequestMapping(value = "/find/{id}", method = RequestMethod.GET)
    public ResponseEntity<Offer> getBrand(@PathVariable Long id) {
        var offer = offerService.findById(id);
        return new ResponseEntity<>(offer, HttpStatus.OK);
    }

    @PostMapping("/add")
    @Operation(security = {@SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME)})
    public ResponseEntity<Offer> add(@RequestBody Offer request) {
        var requestHotel = request.getHotel().stream().findFirst().orElseThrow(() -> new FindException("Hotel with this name already exist !"));
        if (hotelService.findByName(requestHotel.getName()).isPresent()) {
            throw new FindException("Hotel with this name already exist !");
        }
        var country = countryService.findById(request.getHotel().get(0).getCountry().getId());
        var offer = offerService.add(request);

        var hotel = hotelService.addHotel(request.getHotel().get(0), offer, country);
        List<OfferAvailability> availability = new ArrayList<>();

        var availabe = request.getAvailabilities().get(0);
        if (request.getAvailabilities() != null) {
            for (int i = 0; i < 10; i++) {
                availability.add(offerAvailabilityService.add(offer, availabe));
                availabe.setDatetimeEnd(availabe.getDatetimeEnd().plusDays(7));
                availabe.setDatetimeStart(availabe.getDatetimeStart().plusDays(7));
            }
        }

        if (request.getHotel().get(0).getRooms() != null && !request.getHotel().get(0).getRooms().isEmpty()) {
            request.getHotel().get(0).getRooms().forEach(roomRequest -> {
                roomService.add(roomRequest, hotel);
            });
        }

        var respond = offerService.findById(offer.getId());
        respond.setHotel(new ArrayList<>());
        respond.getHotel().add(hotel);
        respond.getHotel().get(0).setRooms(
                new HashSet<>(roomService.findByHotel(respond.getHotel().get(0).getId()))
        );
        respond.setAvailabilities(availability);

        return new ResponseEntity<>(respond, HttpStatus.OK);
    }

    @PutMapping("/update")
    @Operation(security = {@SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME)})
    public ResponseEntity<Offer> update(@RequestBody Offer request) {
        var offer = offerService.update(request);
        return new ResponseEntity<>(offer, HttpStatus.OK);
    }

    @PutMapping("/disable-visibility")
    @Operation(security = {@SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME)})
    public ResponseEntity<HttpStatus> delete(@RequestBody Long id) {
        offerService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK, HttpStatus.OK);
    }

    private List<Offer> getRecommended(List<Offer> offers) {
        var list = new ArrayList<>(offers.stream()
                .sorted((o1, o2) -> {
                    var average1 = o1.getOpinions().stream().mapToDouble(Opinion::getValue).average().orElse(0.0);
                    var average2 = o2.getOpinions().stream().mapToDouble(Opinion::getValue).average().orElse(0.0);
                    return Double.compare(average1, average2);
                }).limit(20).toList());
        Collections.reverse(list);
        return list;
    }
}
