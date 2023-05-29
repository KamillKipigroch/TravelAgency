package com.TravelAgency.rest.offer.service;

import com.TravelAgency.rest.offer.model.Offer;
import com.TravelAgency.rest.offer.offerAvailability.model.OfferAvailability;
import com.TravelAgency.rest.offer.repository.OfferRepository;
import com.TravelAgency.rest.order.service.OrderService;
import com.TravelAgency.rest.room.model.Room;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.lang.module.FindException;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDate;
import java.util.List;
import java.util.Objects;

import static com.TravelAgency.comunicates.Communicates.NOT_FOUND_WITH_ID;

@Service
@AllArgsConstructor
public class OfferService {
    private final OfferRepository offerRepository;

    private final OrderService orderService;

    public List<Offer> findAll() {
        var offers = offerRepository.findAll();
        offers.forEach(offer -> offer.setAvailabilities(
                offer.getAvailabilities().stream().filter(availability ->
                        offer.getHotel().get(0).getRooms().stream().allMatch(room -> verifyRoomIsEmpty(availability, room))
                                && availability.getDatetimeStart().isAfter(ChronoLocalDate.from(LocalDateTime.now()))).toList()));
        return offers;
    }

    private boolean verifyRoomIsEmpty(OfferAvailability offerAvailability, Room room) {
        var count = orderService.findAll().stream().filter(
                order -> !Objects.equals(order.getOrderStatus().getName(), "Cancel") &&Objects.equals(order.getRoom().getId(), room.getId()) && Objects.equals(order.getDeadline().getId(), offerAvailability.getId())).count();
        return room.getQuantity() > count;
    }

    public Offer findById(Long id) {
        var offer = offerRepository.findById(id)
                .orElseThrow(() -> new FindException(NOT_FOUND_WITH_ID + id));
        offer.setAvailabilities(
                offer.getAvailabilities().stream().filter(availability ->
                        offer.getHotel().get(0).getRooms().stream().allMatch(room -> verifyRoomIsEmpty(availability, room))
                                && availability.getDatetimeStart().isAfter(ChronoLocalDate.from(LocalDateTime.now()))).toList());
        return offer;
    }


    public Offer add(Offer offerRequest) {
        var newObject = new Offer();
        newObject.setDescription(offerRequest.getDescription());
        newObject.setCreateDate(LocalDateTime.now());
        newObject.setVisible(true);
        return offerRepository.save(newObject);
    }

    public void delete(Long id) {
        var object = offerRepository.findById(id).orElseThrow(() ->
                new FindException(NOT_FOUND_WITH_ID + id));
        object.setVisible(false);
        offerRepository.save(object);
    }

    public Offer update(Offer request) {
        offerRepository.findById(request.getId()).orElseThrow(() ->
                new FindException(NOT_FOUND_WITH_ID + request.getId()));
        return offerRepository.save(request);
    }
}
