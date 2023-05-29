package com.TravelAgency.rest.order.controller;

import com.TravelAgency.rest.offer.offerAvailability.OfferAvailability;
import com.TravelAgency.rest.offer.offerAvailability.service.OfferAvailabilityService;
import com.TravelAgency.rest.order.OrderRequest;
import com.TravelAgency.rest.order.model.Order;
import com.TravelAgency.rest.order.service.OrderService;
import com.TravelAgency.rest.order.status.service.OrderStatusService;
import com.TravelAgency.rest.room.model.Room;
import com.TravelAgency.rest.room.service.RoomService;
import com.TravelAgency.rest.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.module.FindException;
import java.time.Duration;
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

    @PutMapping("/next-status")
    public ResponseEntity<Order> findNextStatus(@RequestBody Order order) {
        var status = orderStatusService.findNext(order.getOrderStatus());
        order.setOrderStatus(status);
        var response =orderService.update(order);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/cancel")
    public ResponseEntity<Order> cancel(@RequestBody Order order) {
        var status = orderStatusService.findByName("Cancel");
        order.setOrderStatus(status);
        var response =orderService.update(order);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @PostMapping("/add")
    @Operation(security = {@SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME)})
    public ResponseEntity<Order> add(@RequestBody OrderRequest request) {
        var user = userService.findUserByEmail(request.getUserEmail());
        var room = roomService.findById(request.getSelectedRoom());
        var deadline = offerAvailability.findById(request.getOfferAvailabilityId());
        var status = orderStatusService.findAll().stream().findFirst().orElseThrow(() -> new FindException(LIST_IS_EMPTY + " Order Status"));
        var days = Math.abs( Duration.between( deadline.getDatetimeEnd().atStartOfDay(),deadline.getDatetimeStart().atStartOfDay()).toDays() - 1);
        var price = (room.getPrice() * days);
        if (deadline.getPromotion() != null && deadline.getPromotion()) {
            price = (room.getPrice() - deadline.getPromotionPrice()) *days;
        }
        this.validOrder(deadline, room);
        var order = orderService.addOrder(status, deadline, room, user, price);

        return new ResponseEntity<>(order, HttpStatus.OK);
    }


    private void validOrder(OfferAvailability offerAvailability, Room room) {
        var count = orderService.findAll().stream().filter(order -> !Objects.equals(order.getOrderStatus().getName(), "Cancel") && Objects.equals(order.getRoom().getId(), room.getId())
                && Objects.equals(order.getDeadline().getId(), offerAvailability.getId())).count();
        if (room.getQuantity() <= count) {
            throw new IllegalStateException("Selected room is not available on this deadline !");
        }
    }
}
