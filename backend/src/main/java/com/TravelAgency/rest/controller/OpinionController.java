package com.TravelAgency.rest.controller;

import com.TravelAgency.rest.model.database.Opinion;
import com.TravelAgency.rest.model.dto.OpinionRequest;
import com.TravelAgency.rest.model.database.OpinionImage;
import com.TravelAgency.rest.service.OfferService;
import com.TravelAgency.rest.service.OpinionImageService;
import com.TravelAgency.rest.service.OpinionService;
import com.TravelAgency.security.user.service.UserService;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static com.TravelAgency.config.SwaggerConfig.BEARER_KEY_SECURITY_SCHEME;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

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

    @RequestMapping(path = "/upload-image", method = POST, consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<OpinionImage> upload(@RequestPart("opinion") String opinionId, @RequestPart("image") MultipartFile image) throws IOException {
        var opinion = opinionService.findById(Long.parseLong(opinionId));
        var uploadResult = cloudinary.uploader().upload(image.getBytes(), ObjectUtils.emptyMap());
        var opinionImage = opinionImageService.add(opinion, uploadResult.get("url").toString());

        return new ResponseEntity<>(opinionImage, HttpStatus.OK);
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
