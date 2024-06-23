package com.pramu.fasteats.repository;

import com.pramu.fasteats.model.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FoodRepository extends JpaRepository<Food, Long> {

    List<Food> findByRestaurantId(Long restaurantId);

    @Query("SELECT f FROM Food f WHERE f.name LIKE %:keyword% OR f.category.name LIKE  %:keyword%")
    List<Food> searchFood(@Param("keyword") String keyword);
}
