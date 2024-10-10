package com.pramu.fasteats.repository;

import com.pramu.fasteats.model.AddonItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddonItemRepository extends JpaRepository<AddonItem, Long> {
    List<AddonItem> findByRestaurantId(Long id);
}
