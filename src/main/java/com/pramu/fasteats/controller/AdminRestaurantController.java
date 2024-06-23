package com.pramu.fasteats.controller;

import com.pramu.fasteats.model.Restaurant;
import com.pramu.fasteats.model.User;
import com.pramu.fasteats.request.RestaurantRequest;
import com.pramu.fasteats.response.MessageResponse;
import com.pramu.fasteats.service.RestaurantService;
import com.pramu.fasteats.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/admin/restaurant")
public class AdminRestaurantController {

    @Autowired
    private UserService userService;

    @Autowired
    private RestaurantService restaurantService;

    @PostMapping
    public ResponseEntity<Restaurant> createRestaurant(
            @RequestBody RestaurantRequest restaurantRequest,
            @RequestHeader("Authorization") String token) throws Exception {

        User user = userService.findUserByJwtToken(token);
        Restaurant restaurant = restaurantService.createRestaurant(restaurantRequest, user);

        return new ResponseEntity<>(restaurant, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Restaurant> updateRestaurant(
            @RequestBody RestaurantRequest restaurantRequest,
            @RequestHeader("Authorization") String token,
            @PathVariable Long id) throws Exception {

        User user = userService.findUserByJwtToken(token);
        Restaurant restaurant = restaurantService.updateRestaurant(restaurantRequest, id);

        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteRestaurant(
            @RequestHeader("Authorization") String token,
            @PathVariable Long id) throws Exception {

        User user = userService.findUserByJwtToken(token);
        restaurantService.deleteRestaurant(id);

        MessageResponse messageResponse = new MessageResponse();
        messageResponse.setMessage("Restaurant deleted successfully.");
        return new ResponseEntity<>(messageResponse, HttpStatus.OK);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Restaurant> updateRestaurantStatus(
            @RequestHeader("Authorization") String token,
            @PathVariable Long id) throws Exception {

        User user = userService.findUserByJwtToken(token);
        Restaurant restaurant = restaurantService.updateRestaurantStatus(id);

        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<Restaurant> findRestaurantByUserId(
            @RequestHeader("Authorization") String token) throws Exception {

        User user = userService.findUserByJwtToken(token);
        Restaurant restaurant = restaurantService.getRestaurantByUserId(user.getId());
        System.out.println("heyyyyyyyyy" + restaurant);

        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }



}
