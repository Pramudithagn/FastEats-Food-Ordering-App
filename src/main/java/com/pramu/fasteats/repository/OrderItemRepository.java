package com.pramu.fasteats.repository;

import com.pramu.fasteats.model.Orderitem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<Orderitem, Long> {
}
