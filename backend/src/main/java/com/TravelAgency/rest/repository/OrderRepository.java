package com.TravelAgency.rest.repository;

import com.TravelAgency.rest.model.database.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<Order> findById(Long id);


}
