package com.TravelAgency.rest.repository;

import com.TravelAgency.rest.model.database.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface OrderStatusRepository extends JpaRepository<OrderStatus, Long> {
    @Query("SELECT o FROM OrderStatus o WHERE o.visible = true and o.id = :id ")
    Optional<OrderStatus> findById(@Param("id") Long id);

    @Query("SELECT o FROM OrderStatus o WHERE o.visible = true and o.name = :name")
    Optional<OrderStatus> findByName(@Param("name") String name);

    @Query("SELECT o FROM OrderStatus o WHERE o.visible = true ")
    List<OrderStatus> findAll();
}
