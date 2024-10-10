package com.pramu.fasteats.controller;

import com.pramu.fasteats.model.Food;
import com.pramu.fasteats.model.Restaurant;
import com.pramu.fasteats.request.FoodRequest;
import com.pramu.fasteats.response.MessageResponse;
import com.pramu.fasteats.service.FoodService;
import com.pramu.fasteats.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/food")
public class AdminFoodController {

    @Autowired
    private FoodService foodService;

    @Autowired
    private RestaurantService restaurantService;

    @PostMapping
    public ResponseEntity<Food> createFood(@RequestBody FoodRequest foodRequest,
                                           @RequestHeader("Authorization") String token) throws Exception {
        Restaurant restaurant = restaurantService.findRestaurantById(foodRequest.getRestaurantId());
        Food food = foodService.createFood(foodRequest, restaurant, foodRequest.getCategory());

        return new ResponseEntity<>(food, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteFood(@PathVariable long id,
                                           @RequestHeader("Authorization") String token) throws Exception {
        foodService.deleteFood(id);
        MessageResponse messageResponse = new MessageResponse();
        messageResponse.setMessage("Successfully deleted Food");
        return new ResponseEntity<>(messageResponse, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Food> updateFoodAvailabilityStatus(@PathVariable long id,
                                                      @RequestHeader("Authorization") String token) throws Exception {
        Food food = foodService.updateAvailabilityStatus(id);
        return new ResponseEntity<>(food, HttpStatus.OK);
    }
}
