package com.pramu.fasteats.repository;

import com.pramu.fasteats.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {

    public Cart findByUserId(Long userId);
}
