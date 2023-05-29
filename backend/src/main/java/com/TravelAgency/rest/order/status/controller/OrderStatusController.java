package com.TravelAgency.rest.order.status.controller;

import com.TravelAgency.rest.order.status.OrderStatusRequest;
import com.TravelAgency.rest.order.status.model.OrderStatus;
import com.TravelAgency.rest.order.status.service.OrderStatusService;
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
@RequestMapping("/api/order-status")
public class OrderStatusController {
    private final OrderStatusService orderStatusService;

    @GetMapping("/get-all")
    public ResponseEntity<List<OrderStatus>> getAllCategory() {
        var categories = orderStatusService.findAll();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @PostMapping("/add")
    @Operation(security = {@SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME)})
    public ResponseEntity<OrderStatus> addCategory(@RequestBody OrderStatusRequest request) {
        var roomDetails = orderStatusService.add(request);
        return new ResponseEntity<>(roomDetails, HttpStatus.OK);
    }

    @PutMapping("/update")
    @Operation(security = {@SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME)})
    public ResponseEntity<OrderStatus> updateCategory(@RequestBody OrderStatus request) {
        var roomDetails = orderStatusService.update(request);
        return new ResponseEntity<>(roomDetails, HttpStatus.OK);
    }

    @PutMapping("/disable-visibility")
    @Operation(security = {@SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME)})
    public ResponseEntity<HttpStatus> deleteCategory(@RequestBody Long id) {
        orderStatusService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK,HttpStatus.OK);
    }
}
