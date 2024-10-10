package com.pramu.fasteats.repository;

import com.pramu.fasteats.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
        public Cart findByCustomer_Id(Long userId);
}
