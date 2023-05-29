package com.TravelAgency.rest.order.status.service;

import com.TravelAgency.rest.order.status.OrderStatusRequest;
import com.TravelAgency.rest.order.status.model.OrderStatus;
import com.TravelAgency.rest.order.status.repository.OrderStatusRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.lang.module.FindException;
import java.util.Comparator;
import java.util.List;

import static com.TravelAgency.comunicates.Communicates.*;


@Service
@AllArgsConstructor
public class OrderStatusService {
    private final OrderStatusRepository orderStatusRepository;

    public List<OrderStatus> findAll() {
        Comparator<OrderStatus> comparator =
                (OrderStatus o1, OrderStatus o2) -> Long.compare(o1.getLevel(), o2.getLevel());
        var list = orderStatusRepository.findAll();
        list.sort(comparator);
        return list;
    }

    public OrderStatus findById(Long id) {
        return orderStatusRepository.findById(id)
                .orElseThrow(() -> new FindException(NOT_FOUND_WITH_ID + id));
    }

    public OrderStatus findByName(String name) {
        return orderStatusRepository.findByName(name)
                .orElseThrow(() -> new FindException(NOT_FOUND_WITH_NAME + name));
    }

    public OrderStatus add(OrderStatusRequest request) {
        if (orderStatusRepository.findByName(request.getName()).isPresent()) {
            throw new FindException(IS_ALREADY_EXIST + request.getName());
        }

        var newObject = new OrderStatus();

        var all = orderStatusRepository.findAll();
        all.sort((OrderStatus o1, OrderStatus o2) -> Long.compare(o1.getLevel(), o2.getLevel()));
        if(all.stream().allMatch(status -> status.getLevel() < request.getLevel())){
            all.forEach(orderStatus -> {orderStatus.setItsFinal(false);});
            orderStatusRepository.saveAll(all);
            newObject.setItsFinal(true);
        }
        else {
            newObject.setItsFinal(false);
        }


        newObject.setLevel(request.getLevel());
        newObject.setVisible(true);
        newObject.setName(request.getName());
        return orderStatusRepository.save(newObject);
    }

    public OrderStatus update(OrderStatus category) {
        var update = orderStatusRepository.findById(category.getId()).orElseThrow(() ->
                new FindException(NOT_FOUND_WITH_ID + category.getId()));
        update.setLevel(category.getLevel());
        update.setName(category.getName());
        update.setVisible(category.getVisible());
        return orderStatusRepository.save(update);
    }

    public void delete(Long id) {
        var object = orderStatusRepository.findById(id).orElseThrow(() ->
                new FindException(NOT_FOUND_WITH_ID + id));
        object.setVisible(false);
        orderStatusRepository.save(object);
        if (orderStatusRepository.findAll().stream().noneMatch(OrderStatus::getVisible)) {
            object.setVisible(true);
            orderStatusRepository.save(object);
            throw new IllegalStateException("You cant disable all objects !");
        }
    }

    public OrderStatus findNext(OrderStatus orderStatus) {
        var list = orderStatusRepository.findAll().stream().filter(status -> !status.getName().equals("Cancel")).toList();
        return list.stream().filter(status -> status.getLevel() > orderStatus.getLevel()).findFirst().orElse(null);
    }
}
