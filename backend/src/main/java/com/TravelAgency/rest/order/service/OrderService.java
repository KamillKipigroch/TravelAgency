package com.TravelAgency.rest.order.service;

import com.TravelAgency.rest.offer.offerAvailability.model.OfferAvailability;
import com.TravelAgency.rest.order.model.Order;
import com.TravelAgency.rest.order.repository.OrderRepository;
import com.TravelAgency.rest.order.status.model.OrderStatus;
import com.TravelAgency.rest.room.model.Room;
import com.TravelAgency.rest.user.model.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.lang.module.FindException;
import java.util.List;

import static com.TravelAgency.comunicates.Communicates.NOT_FOUND_WITH_ID;


@Service
@AllArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public List<Order> findUserOrders(User user) {
        return orderRepository.findAll().stream().filter(order -> order.getUser().getEmail().equals(user.getEmail())).toList();
    }

    public Order findById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new FindException(NOT_FOUND_WITH_ID + id));
    }
    public Order addOrder(OrderStatus status, OfferAvailability deadline, Room room, User user, double price) {
        var order = new Order();
        order.setOrderStatus(status);
        order.setDeadline(deadline);
        order.setRoom(room);
        order.setUser(user);
        order.setVisible(true);
        order.setPrice(price);
        return orderRepository.save(order);
    }

    public Order update(Order order) {
        var o = orderRepository.findById(order.getId()).orElseThrow( () -> new FindException(NOT_FOUND_WITH_ID + order.getId()));
        o.setOrderStatus(order.getOrderStatus());
        return orderRepository.save(o);
    }
}
