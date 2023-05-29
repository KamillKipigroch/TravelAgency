package com.TravelAgency.rest.image.controller;

import com.TravelAgency.rest.image.offer.model.OfferImage;
import com.TravelAgency.rest.image.offer.service.OfferImageService;
import com.TravelAgency.rest.image.opinion.model.OpinionImage;
import com.TravelAgency.rest.image.opinion.service.OpinionImageService;
import com.TravelAgency.rest.image.room.model.RoomImage;
import com.TravelAgency.rest.image.room.service.RoomImageService;
import com.TravelAgency.rest.image.service.UploadService;
import com.TravelAgency.rest.offer.service.OfferService;
import com.TravelAgency.rest.opinion.service.OpinionService;
import com.TravelAgency.rest.room.service.RoomService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@AllArgsConstructor
@RequestMapping("/api/image")
public class ImageController {
    private final OfferService offerService;
    private final OpinionService opinionService;
    private final OfferImageService offerImageService;
    private final OpinionImageService opinionImageService;
    private final RoomImageService roomImageService;
    private final RoomService roomService;

    private final UploadService uploadService;

    @RequestMapping(path = "/upload-offer-image", method = POST, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<OfferImage> uploadOfferImage(@RequestPart("offer") String offerId, @RequestPart("image") MultipartFile image) throws IOException {
        var offer = offerService.findById(Long.parseLong(offerId));
        var uploadResult = uploadService.uploadImage(image.getBytes());
        var opinionImage = offerImageService.add(offer, uploadResult.get("url").toString());

        return new ResponseEntity<>(opinionImage, HttpStatus.OK);
    }

    @RequestMapping(path = "/upload-room-image", method = POST, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<RoomImage> uploadRoomImage(@RequestPart("room") String roomId, @RequestPart("image") MultipartFile image) throws IOException {
        var room = roomService.findById(Long.parseLong(roomId));
        var uploadResult = uploadService.uploadImage(image.getBytes());
        var opinionImage = roomImageService.add(room, uploadResult.get("url").toString());

        return new ResponseEntity<>(opinionImage, HttpStatus.OK);
    }

    @RequestMapping(path = "/upload-opinion-image", method = POST, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<OpinionImage> uploadOpinionImage(@RequestPart("opinion") String opinionId, @RequestPart("image") MultipartFile image) throws IOException {
        var opinion = opinionService.findById(Long.parseLong(opinionId));
        var uploadResult = uploadService.uploadImage(image.getBytes());
        var opinionImage = opinionImageService.add(opinion, uploadResult.get("url").toString());

        return new ResponseEntity<>(opinionImage, HttpStatus.OK);
    }
}
