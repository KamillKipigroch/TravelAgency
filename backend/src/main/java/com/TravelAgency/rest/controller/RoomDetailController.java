package com.TravelAgency.rest.controller;

import com.TravelAgency.rest.model.ModelDTO;
import com.TravelAgency.rest.model.roomDetail.RoomDetail;
import com.TravelAgency.rest.model.roomDetail.RoomDetailRequest;
import com.TravelAgency.rest.service.RoomDetailService;
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
@RequestMapping("/api/room-detail")
public class RoomDetailController {
    private final RoomDetailService roomDetailService;

    @GetMapping("/get-all")
    public ResponseEntity<List<RoomDetail>> getAllCategory() {
        var categories = roomDetailService.findAll();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @PostMapping("/add")
    @Operation(security = {@SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME)})
    public ResponseEntity<RoomDetail> addCategory(@RequestBody RoomDetailRequest request) {
        var roomDetails = roomDetailService.add(request);
        return new ResponseEntity<>(roomDetails, HttpStatus.OK);
    }

    @PutMapping("/update")
    @Operation(security = {@SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME)})
    public ResponseEntity<RoomDetail> updateCategory(@RequestBody RoomDetail request) {
        var roomDetails = roomDetailService.update(request);
        return new ResponseEntity<>(roomDetails, HttpStatus.OK);
    }

    @PutMapping("/disable-visibility")
    @Operation(security = {@SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME)})
    public ResponseEntity<HttpStatus> deleteCategory(@RequestBody Long id) {
        roomDetailService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK,HttpStatus.OK);
    }
}
