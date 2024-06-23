package com.pramu.fasteats.service;

import com.pramu.fasteats.dto.RestaurantDto;
import com.pramu.fasteats.model.Address;
import com.pramu.fasteats.model.Restaurant;
import com.pramu.fasteats.model.User;
import com.pramu.fasteats.repository.AddressRepository;
import com.pramu.fasteats.repository.RestaurantRepository;
import com.pramu.fasteats.repository.UserRepository;
import com.pramu.fasteats.request.RestaurantRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public Restaurant findRestaurantById(Long id) throws Exception {
        Optional<Restaurant> restaurant = restaurantRepository.findById(id);
        if (restaurant.isEmpty()) {
            throw new Exception(id + "Restaurant not found");
        }
        return restaurant.get();
    }

    @Override
    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    @Override
    public List<Restaurant> searchRestaurants(String searchQuery) {
        return restaurantRepository.findBySearchQuery(searchQuery);
    }

    @Override
    public Restaurant getRestaurantByUserId(Long userId) throws Exception {
        Restaurant restaurant = restaurantRepository.findByOwnerId(userId);
        if (restaurant == null) {
            throw new Exception("Owner id " + userId + "'s Restaurant not found");
        }
        return restaurant;
    }

    @Override
    public Restaurant createRestaurant(RestaurantRequest restaurantRequest, User user) {
        Address address = addressRepository.save(restaurantRequest.getAddress());

        Restaurant restaurant = new Restaurant();
        restaurant.setName(restaurantRequest.getName());
        restaurant.setAddress(address);
        restaurant.setDescription(restaurantRequest.getDescription());
        restaurant.setImages(restaurantRequest.getImages());
        restaurant.setCuisineType(restaurantRequest.getCuisineType());
        restaurant.setOwner(user);
        restaurant.setOpeningHours(restaurantRequest.getOpeningHours());
        restaurant.setRegistrationDate(LocalDateTime.now());
        restaurant.setContactInformation(restaurantRequest.getContactInformation());

        return restaurantRepository.save(restaurant);
    }

    @Override
    public Restaurant updateRestaurant(RestaurantRequest restaurantRequest, Long restaurantId) throws Exception {
        Restaurant restaurant = findRestaurantById(restaurantId);

        if (restaurant.getName() != null) {
            restaurant.setName(restaurantRequest.getName());
        }

        if (restaurant.getDescription() != null) {
            restaurant.setDescription(restaurantRequest.getDescription());
        }

        if (restaurant.getCuisineType() != null) {
            restaurant.setCuisineType(restaurantRequest.getCuisineType());
        }

        return restaurantRepository.save(restaurant);
    }

    @Override
    public void deleteRestaurant(Long restaurantId) throws Exception {
        Restaurant restaurant = findRestaurantById(restaurantId);
        restaurantRepository.delete(restaurant);
    }

    @Override
    public RestaurantDto addFavourites(Long restaurantId, User user) throws Exception {
        Restaurant restaurant = findRestaurantById(restaurantId);
        RestaurantDto restaurantDto = new RestaurantDto();
        restaurantDto.setId(restaurant.getId());
        restaurantDto.setTitle(restaurant.getName());
        restaurantDto.setImages(restaurant.getImages());
        restaurantDto.setDescription(restaurant.getDescription());

//        if(user.getFavourites().contains(restaurantDto)){
//            user.getFavourites().remove(restaurantDto);
//        }
//        else user.getFavourites().add(restaurantDto);

        boolean isFavourited = false;
        List<RestaurantDto> favouritesList = user.getFavourites();
        for (RestaurantDto favourite : favouritesList) {
            if (favourite.getId().equals(restaurantId)) {
                isFavourited = true;
                break;
            }
        }

        if (isFavourited) {
            favouritesList.removeIf(favourite -> favourite.getId().equals(restaurantId));
        }
        else {
            favouritesList.add(restaurantDto);
        }

        userRepository.save(user);
        return restaurantDto;
    }

    @Override
    public Restaurant updateRestaurantStatus(Long restaurantId) throws Exception {
        Restaurant restaurant = findRestaurantById(restaurantId);
        restaurant.setOpen(!restaurant.isOpen());
        return restaurantRepository.save(restaurant);
    }
}
