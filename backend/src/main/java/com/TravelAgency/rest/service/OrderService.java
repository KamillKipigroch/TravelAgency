package com.TravelAgency.rest.service;

import com.TravelAgency.rest.model.database.OfferAvailability;
import com.TravelAgency.rest.model.database.Order;
import com.TravelAgency.rest.model.database.OrderStatus;
import com.TravelAgency.rest.model.database.Room;
import com.TravelAgency.rest.repository.OrderRepository;
import com.TravelAgency.security.user.model.User;
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
    public Order addOrder(OrderStatus status, OfferAvailability deadline, Room room, User user) {
        var order = new Order();
        order.setOrderStatus(status);
        order.setDeadline(deadline);
        order.setRoom(room);
        order.setUser(user);
        return orderRepository.save(order);
    }
}
