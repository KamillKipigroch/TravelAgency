package com.TravelAgency.rest.controller;

import com.TravelAgency.rest.model.offer.Offer;
import com.TravelAgency.rest.model.offer.OfferRequest;
import com.TravelAgency.rest.model.offerAvailability.OfferAvailability;
import com.TravelAgency.rest.model.offerImage.OfferImage;
import com.TravelAgency.rest.model.roomDetail.RoomDetail;
import com.TravelAgency.rest.service.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.module.FindException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.TravelAgency.config.SwaggerConfig.BEARER_KEY_SECURITY_SCHEME;

@RestController
@AllArgsConstructor
@RequestMapping("/api/offer")
public class OfferController {
    private final OfferService offerService;
    private final CountryService countryService;
    private final HotelService hotelService;
    private final OfferImageService offerImageService;
    private final RoomImageService roomImageService;
    private final OfferAvailabilityService offerAvailabilityService;
    private final RoomDetailService roomDetailService;
    private final OpinionService opinionService;
    private final OrderService orderService;
    private final RoomService roomService;

    @GetMapping("/get-all")
    public ResponseEntity<List<Offer>> getAll() {
        var offers = offerService.findAll();
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

        var country = countryService.findById(request.getCountry().getId());
        var offer = offerService.add(request, country);

        var hotel = hotelService.addHotel(request.getHotel().get(0), offer);
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
                var room = roomService.add(roomRequest, hotel);
            });
        }
        var respond = offerService.findById(offer.getId());
        respond.setHotel(new ArrayList<>());
        respond.getHotel().add(hotel);
        respond.setAvailabilities(availability);
        return new ResponseEntity<>(respond, HttpStatus.OK);
    }

    @PutMapping("/update")
    @Operation(security = {@SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME)})
    public ResponseEntity<Offer> update(@RequestBody Offer request) {
//        var roomDetails = opinionService.update(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/disable-visibility")
    @Operation(security = {@SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME)})
    public ResponseEntity<HttpStatus> delete(@RequestBody Long id) {
        offerService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK, HttpStatus.OK);
    }
}
