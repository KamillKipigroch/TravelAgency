package com.TravelAgency.rest.repository;

import com.TravelAgency.rest.model.database.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("SELECT o FROM Order o WHERE o.visible = true and o.id = :id")
    Optional<Order> findById(@Param("id") Long id);

    @Query("SELECT o FROM Order o WHERE o.visible = true ")
    List<Order> findAll();
}
