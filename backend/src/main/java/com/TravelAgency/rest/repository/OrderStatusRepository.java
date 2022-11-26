package com.TravelAgency.rest.repository;

import com.TravelAgency.rest.model.orderStatus.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderStatusRepository extends JpaRepository<OrderStatus, Long> {
    Optional<OrderStatus> findById(Long id);

    Optional<OrderStatus> findByName(String name);
}
