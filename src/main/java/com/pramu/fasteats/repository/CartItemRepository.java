package com.pramu.fasteats.repository;

import com.pramu.fasteats.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}
