package com.pramu.fasteats.repository;

import com.pramu.fasteats.model.AddonCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddonCategoryRepository extends JpaRepository<AddonCategory, Long> {
    List<AddonCategory> findByRestaurantId(Long restaurantId);
}
