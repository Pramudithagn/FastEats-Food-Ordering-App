package com.pramu.fasteats.repository;

import com.pramu.fasteats.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    public List<Order> findByUserId(Long userId);

    public List<Order> findByRestaurantId(Long restaurantId);

}
