package com.pramu.fasteats.controller;

import com.pramu.fasteats.dto.RestaurantDto;
import com.pramu.fasteats.model.Restaurant;
import com.pramu.fasteats.model.User;
import com.pramu.fasteats.service.RestaurantService;
import com.pramu.fasteats.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurant")
public class RestaurantController {

    @Autowired
    private UserService userService;

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping("/search")
    public ResponseEntity<List<Restaurant>> searchRestaurant(
            @RequestParam String searchQuery,
            @RequestHeader("Authorization") String token) throws Exception {

        List<Restaurant> restaurants = restaurantService.searchRestaurants(searchQuery);
        return new ResponseEntity<>(restaurants, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<Restaurant>> getAllRestaurant(
            @RequestHeader("Authorization") String token) throws Exception {

        List<Restaurant> restaurants = restaurantService.getAllRestaurants();
        return new ResponseEntity<>(restaurants, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurant> findRestaurantById(
            @RequestHeader("Authorization") String token,
            @PathVariable Long id) throws Exception {

        Restaurant restaurants = restaurantService.findRestaurantById(id);
        return new ResponseEntity<>(restaurants, HttpStatus.OK);
    }

    @PutMapping("/{id}/add-favourite")
    public ResponseEntity<RestaurantDto> addFavourite(
            @RequestHeader("Authorization") String token,
            @PathVariable Long id) throws Exception {

        User user = userService.findUserByJwtToken(token);
        RestaurantDto restaurantDto = restaurantService.addFavourites(id, user);
        return new ResponseEntity<>(restaurantDto, HttpStatus.OK);
    }

}
