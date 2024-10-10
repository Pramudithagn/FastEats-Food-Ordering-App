package com.pramu.fasteats.controller;

import com.pramu.fasteats.model.Food;
import com.pramu.fasteats.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/food")
public class FoodController {

    @Autowired
    private FoodService foodService;

    @GetMapping("/search")
    public ResponseEntity<List<Food>> searchFood(@RequestParam String name,
                                           @RequestHeader("Authorization") String token) throws Exception {
        List<Food> foods = foodService.searchFood(name);
        return new ResponseEntity<>(foods, HttpStatus.OK);
    }

    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<List<Food>> getRestaurantFood(@PathVariable Long restaurantId,
                                                        @RequestHeader("Authorization") String token,
                                                        @RequestParam(required = false) boolean vegetarian,
                                                        @RequestParam(required = false) boolean nonVegetarian,
                                                        @RequestParam(required = false) boolean seasonal,
                                                        @RequestParam(required = false) String category) throws Exception {
        List<Food> foods = foodService.getRestaurantFoods(restaurantId, category, vegetarian, nonVegetarian, seasonal);
        return new ResponseEntity<>(foods, HttpStatus.OK);
    }
}
