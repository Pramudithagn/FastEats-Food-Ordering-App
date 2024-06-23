package com.pramu.fasteats.service;

import com.pramu.fasteats.model.Category;
import com.pramu.fasteats.model.Food;
import com.pramu.fasteats.model.Restaurant;
import com.pramu.fasteats.repository.FoodRepository;
import com.pramu.fasteats.request.FoodRequest;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FoodServiceImpl implements FoodService {

    @Autowired
    private FoodRepository foodRepository;

    @Override
    public Food findFoodById(Long id) {
        Optional<Food> food = foodRepository.findById(id);
        if (food.isEmpty()) throw new EntityNotFoundException("Food not found !");
        return food.get();
    }

    @Override
    public List<Food> findAllFood() {
        return List.of();
    }

    @Override
    public List<Food> searchFood(String keyword) {
        return foodRepository.searchFood(keyword);
    }

    @Override
    public List<Food> getRestaurantFoods(Long restaurantId, String category, boolean isVeg, boolean isNonVeg, boolean isSeasonal) throws Exception {
        List<Food> foods = foodRepository.findByRestaurantId(restaurantId);

        if(isVeg){
            foods = filterByVegetarian(foods, isVeg);
        }
        if(isNonVeg){
            foods = filterByNonVegetarian(foods, isNonVeg);
        }
        if(isSeasonal){
            foods = filterBySeasonal(foods, isSeasonal);
        }
        if (category != null && !category.equals("")) {
            foods = filterByCategory(foods, category);
        }

        return foods;
    }

    private List<Food> filterByCategory(List<Food> foods, String category) {
        return foods.stream().filter(food -> {
            if(food.getCategory() != null){
                return food.getCategory().getName().equals(category);
            }
            return false;
        }).collect(Collectors.toList());
    }

    private List<Food> filterBySeasonal(List<Food> foods, boolean isSeasonal) {
        return foods.stream().filter(food -> food.isSeasonal()==isSeasonal).collect(Collectors.toList());
    }

    private List<Food> filterByNonVegetarian(List<Food> foods, boolean isNonVeg) {
        return foods.stream().filter(food -> food.isVegetarian()==false).collect(Collectors.toList());
    }

    private List<Food> filterByVegetarian(List<Food> foods, boolean isVeg) {
        return foods.stream().filter(food -> food.isVegetarian()==isVeg).collect(Collectors.toList());
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
        Food food = findFoodById(foodId);
        food.setAvailable(!food.isAvailable());

        return foodRepository.save(food);
    }

    @Override
    public void deleteFood(Long foodId) throws Exception {
        Food food = findFoodById(foodId);
        food.setRestaurant(null);
        foodRepository.save(food);
    }
}
