package com.pramu.fasteats.service;

import com.pramu.fasteats.model.Category;
import com.pramu.fasteats.model.Food;
import com.pramu.fasteats.model.Restaurant;
import com.pramu.fasteats.repository.FoodRepository;
import com.pramu.fasteats.request.FoodRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodServiceImpl implements FoodService {

    @Autowired
    private FoodRepository foodRepository;

    @Override
    public Food findFoodById(Long id) {
        return null;
    }

    @Override
    public List<Food> findAllFood() {
        return List.of();
    }

    @Override
    public List<Food> searchFood(String keyword) {
        return List.of();
    }

    @Override
    public List<Food> getRestaurantFoods(Long restaurantId, String category, boolean isVeg, boolean isNonVeg, boolean isSeasonal) throws Exception {
        return List.of();
    }

    @Override
    public Food createFood(FoodRequest request, Restaurant restaurant, Category category) {
        Food food = new Food();
        food.setRestaurant(restaurant);
        food.setCategory(category);
        food.setName(request.getName());
        food.setPrice(request.getPrice());
        food.setDescription(request.getDescription());
        food.setSeasonal(request.isSeasonal());
        food.setVegetarian(request.isVegetarian());
        food.setIngredients(request.getIngrediants());
        food.setImages(request.getImages());

        Food savedFood = foodRepository.save(food);
        restaurant.getFoods().add(savedFood);

        return savedFood;
    }

    @Override
    public Food updateAvailabilityStatus(Long foodId) throws Exception {
        return null;
    }

    @Override
    public void deleteFood(Long foodId) throws Exception {

    }
}
