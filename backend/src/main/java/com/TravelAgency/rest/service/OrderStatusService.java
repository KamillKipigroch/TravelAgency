package com.TravelAgency.rest.service;

import com.TravelAgency.rest.model.orderStatus.OrderStatus;
import com.TravelAgency.rest.model.orderStatus.OrderStatusRequest;
import com.TravelAgency.rest.repository.OrderStatusRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.lang.module.FindException;
import java.util.List;

import static com.TravelAgency.comunicates.Communicates.IS_ALREADY_EXIST;
import static com.TravelAgency.comunicates.Communicates.NOT_FOUND_WITH_ID;


@Service
@AllArgsConstructor
public class OrderStatusService {
    private final OrderStatusRepository orderStatusRepository;

    public List<OrderStatus> findAll() {
        return orderStatusRepository.findAll();
    }

    public OrderStatus findById(Long id) {
        return orderStatusRepository.findById(id)
                .orElseThrow(() -> new FindException(NOT_FOUND_WITH_ID + id));
    }

    public OrderStatus add(OrderStatusRequest request) {
        if (orderStatusRepository.findByName(request.getName()).isPresent()) {
            throw new FindException(IS_ALREADY_EXIST + request.getName());
        }
        var newObject = new OrderStatus();
        newObject.setVisible(true);
        newObject.setName(request.getName());
        return orderStatusRepository.save(newObject);
    }

    public OrderStatus update(OrderStatus category) {
        var update = orderStatusRepository.findById(category.getId()).orElseThrow(() ->
                new FindException(NOT_FOUND_WITH_ID + category.getId()));
        update.setName(category.getName());
        update.setVisible(category.getVisible());
        return orderStatusRepository.save(update);
    }

    public void delete(Long id) {
        var object = orderStatusRepository.findById(id).orElseThrow(() ->
                new FindException(NOT_FOUND_WITH_ID + id));
        object.setVisible(false);
        orderStatusRepository.save(object);
        if(orderStatusRepository.findAll().stream().noneMatch(OrderStatus::getVisible)){
            object.setVisible(true);
            orderStatusRepository.save(object);
            throw new IllegalStateException("You cant disable all objects !");
        }
    }
}
