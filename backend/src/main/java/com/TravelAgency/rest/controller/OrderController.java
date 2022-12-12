package com.TravelAgency.rest.controller;

import com.TravelAgency.rest.model.offerAvailability.OfferAvailability;
import com.TravelAgency.rest.model.opinion.Opinion;
import com.TravelAgency.rest.model.opinion.OpinionRequest;
import com.TravelAgency.rest.model.order.Order;
import com.TravelAgency.rest.model.order.OrderRequest;
import com.TravelAgency.rest.model.orderStatus.OrderStatus;
import com.TravelAgency.rest.model.room.Room;
import com.TravelAgency.rest.service.OfferAvailabilityService;
import com.TravelAgency.rest.service.OrderService;
import com.TravelAgency.rest.service.OrderStatusService;
import com.TravelAgency.rest.service.RoomService;
import com.TravelAgency.security.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.module.FindException;
import java.util.List;
import java.util.Objects;

import static com.TravelAgency.comunicates.Communicates.LIST_IS_EMPTY;
import static com.TravelAgency.config.SwaggerConfig.BEARER_KEY_SECURITY_SCHEME;

@RestController
@AllArgsConstructor
@RequestMapping("/api/order")
public class OrderController {
    private final OrderService orderService;
    private final UserService userService;
    private final OrderStatusService orderStatusService;
    private final OfferAvailabilityService offerAvailability;
    private final RoomService roomService;

    @GetMapping("/get-all")
    public ResponseEntity<List<Order>> getAll() {
        var orders = orderService.findAll();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/get-by-user")
    public ResponseEntity<List<Order>> findUserOrders(@RequestParam String userEmail) {
        var user = userService.findUserByEmail(userEmail);
        var orders = orderService.findUserOrders(user);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @PostMapping("/add")
    @Operation(security = {@SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME)})
    public ResponseEntity<Order> add(@RequestBody OrderRequest request) {
        var user = userService.findUserByEmail(request.getUserEmail());
        var room = roomService.findById(request.getSelectedRoom());
        var deadline = offerAvailability.findById(request.getOfferAvailabilityId());
        var status = orderStatusService.findAll().stream().findFirst().orElseThrow(() -> new FindException(LIST_IS_EMPTY + " Order Status"));

        this.validOrder(deadline, room);
        var order = orderService.addOrder(status, deadline, room, user);

        return new ResponseEntity<>(order, HttpStatus.OK);
    }


    private void validOrder(OfferAvailability offerAvailability, Room room) {
        var count = orderService.findAll().stream().filter(order -> Objects.equals(order.getRoom().getId(), room.getId()) && Objects.equals(order.getDeadline().getId(), offerAvailability.getId())).count();
        if (room.getQuantity() <= count) {
            throw new IllegalStateException("Selected room is not available on this deadline !");
        }
    }
}
