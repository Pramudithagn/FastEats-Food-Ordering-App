package com.pramu.fasteats.service;

import com.pramu.fasteats.model.Category;
import com.pramu.fasteats.model.Food;
import com.pramu.fasteats.model.Restaurant;
import com.pramu.fasteats.request.FoodRequest;

import java.util.List;

public interface FoodService {

    public Food findFoodById(Long id);

    public List<Food> findAllFood();

    public List<Food> searchFood(String keyword);

    public List<Food> getRestaurantFoods(Long restaurantId, String category, boolean isVeg, boolean isNonVeg, boolean isSeasonal) throws Exception;

    public Food createFood(FoodRequest request, Restaurant restaurant, Category category);

    public Food updateAvailabilityStatus(Long foodId) throws Exception;

    void deleteFood(Long foodId) throws Exception;



}
