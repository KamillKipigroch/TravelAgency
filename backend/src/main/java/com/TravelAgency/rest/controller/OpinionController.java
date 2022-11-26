package com.TravelAgency.rest.controller;

import com.TravelAgency.rest.model.opinion.Opinion;
import com.TravelAgency.rest.model.opinion.OpinionRequest;
import com.TravelAgency.rest.service.OfferService;
import com.TravelAgency.rest.service.OpinionService;
import com.TravelAgency.security.user.service.UserService;
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
    private final OpinionService opinionService;
    private final UserService userService;
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
        var offer = offerService.findByOfferCode(request.getOfferCode());
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
