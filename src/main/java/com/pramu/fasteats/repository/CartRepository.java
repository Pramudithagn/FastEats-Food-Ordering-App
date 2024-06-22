package com.pramu.fasteats.repository;

import com.pramu.fasteats.model.Cart;
import org.springframework.data.repository.CrudRepository;

public interface CartRepository extends CrudRepository<Cart, Long> {
}
