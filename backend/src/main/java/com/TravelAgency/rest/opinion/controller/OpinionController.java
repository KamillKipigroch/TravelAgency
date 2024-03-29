package com.TravelAgency.rest.opinion.controller;

import com.TravelAgency.rest.image.opinion.service.OpinionImageService;
import com.TravelAgency.rest.offer.service.OfferService;
import com.TravelAgency.rest.opinion.OpinionRequest;
import com.TravelAgency.rest.opinion.model.Opinion;
import com.TravelAgency.rest.opinion.service.OpinionService;
import com.TravelAgency.rest.user.service.UserService;
import com.cloudinary.Cloudinary;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.TravelAgency.config.SwaggerConfig.BEARER_KEY_SECURITY_SCHEME;

@RestController
@AllArgsConstructor
@RequestMapping("/api/opinion")
public class OpinionController {

    private final Cloudinary cloudinary;
    private final OpinionService opinionService;
    private final UserService userService;
    private final OpinionImageService opinionImageService;
    private final OfferService offerService;

    @GetMapping("/get-all")
    public ResponseEntity<List<Opinion>> getAll() {
        var categories = opinionService.findAll();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @PostMapping("/add")
    @Operation(security = {@SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME)})
    public ResponseEntity<Opinion> add(@RequestBody OpinionRequest request) {
        var user = userService.findUserByEmail(request.getUserEmail());
        var offer = offerService.findById(request.getOfferId());
        var roomDetails = opinionService.add(request, user, offer);
        return new ResponseEntity<>(roomDetails, HttpStatus.OK);
    }

    @PutMapping("/update")
    @Operation(security = {@SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME)})
    public ResponseEntity<Opinion> update(@RequestBody Opinion request) {
        var roomDetails = opinionService.update(request);
        return new ResponseEntity<>(roomDetails, HttpStatus.OK);
    }

    @PutMapping("/disable-visibility")
    @Operation(security = {@SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME)})
    public ResponseEntity<HttpStatus> delete(@RequestBody Long id) {
        opinionService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK, HttpStatus.OK);
    }
}
