package com.pramu.fasteats.repository;

import com.pramu.fasteats.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    public List<Category> findByRestaurantId(Long restaurantId);
}
