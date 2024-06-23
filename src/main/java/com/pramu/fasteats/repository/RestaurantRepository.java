package com.pramu.fasteats.repository;

import com.pramu.fasteats.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    Restaurant findByOwnerId(long id);

    @Query("SELECT r FROM Restaurant r WHERE lower(r.name) LIKE lower(concat('%',:searchQuery,'%'))"
            + "OR lower(r.cuisineType) LIKE lower(concat('%',:searchQuery,'%') )")
    List<Restaurant> findBySearchQuery(String searchQuery);
}
