package com.pramu.fasteats.service;

import com.pramu.fasteats.dto.RestaurantDto;
import com.pramu.fasteats.model.Restaurant;
import com.pramu.fasteats.model.User;
import com.pramu.fasteats.request.RestaurantRequest;

import java.util.List;

public interface RestaurantService {

    public Restaurant findRestaurantById(Long id) throws Exception;

    public List<Restaurant> getAllRestaurants();

    public List<Restaurant> searchRestaurants(String searchQuery);

    public Restaurant getRestaurantByUserId(Long userId) throws Exception;

    public Restaurant createRestaurant(RestaurantRequest restaurantRequest, User user);

    public Restaurant updateRestaurant(RestaurantRequest restaurantRequest, Long restaurantId) throws Exception;

    public  void deleteRestaurant(Long restaurantId) throws Exception;

    public RestaurantDto addFavourites(Long restaurantId, User user) throws Exception;

    public Restaurant updateRestaurantStatus(Long restaurantId) throws Exception;

}
