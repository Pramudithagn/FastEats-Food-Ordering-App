package com.pramu.fasteats.service;

import com.pramu.fasteats.model.IngredientCategory;
import com.pramu.fasteats.model.IngredientItem;
import com.pramu.fasteats.model.Restaurant;
import com.pramu.fasteats.repository.IngredientCategoryRepository;
import com.pramu.fasteats.repository.IngredientItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IngredientServiceImpl implements IngredientService {

    @Autowired
    private IngredientCategoryRepository ingredientCategoryRepository;

    @Autowired
    private IngredientItemRepository ingredientItemRepository;

    @Autowired
    private RestaurantService restaurantService;

    @Override
    public IngredientCategory findIngredientCategoryById(Long id) throws Exception {
        Optional<IngredientCategory> ingredientCategory = ingredientCategoryRepository.findById(id);

        if(ingredientCategory.isEmpty()){
            throw new Exception("IngredientCategory not found");
        }
        return ingredientCategory.get();
    }

    @Override
    public List<IngredientCategory> findIngredientCategoryByRestaurantId(Long id) throws Exception {
        restaurantService.findRestaurantById(id); //to check restaurant is present or not
        return ingredientCategoryRepository.findByRestaurantId(id);
    }

    @Override
    public IngredientCategory createIngredientCategory(String name, Long restaurantId) throws Exception {

        Restaurant restaurant = restaurantService.findRestaurantById(restaurantId);
        IngredientCategory ingredientCategory = new IngredientCategory();
        ingredientCategory.setName(name);
        ingredientCategory.setRestaurant(restaurant);

        return ingredientCategoryRepository.save(ingredientCategory);
    }

    @Override
    public List<IngredientItem> findRestaurantIngredientItems(Long restaurantId) throws Exception {
        return ingredientItemRepository.findByRestaurantId(restaurantId);
    }

    @Override
    public IngredientItem ingredientItemStockUpdate(Long id) throws Exception {
        Optional<IngredientItem> optIngredientItem = ingredientItemRepository.findById(id);

        if(optIngredientItem.isEmpty()){
            throw new Exception("IngredientItem not found");
        }

        IngredientItem ingredientItem = optIngredientItem.get();
        ingredientItem.setInStock(!ingredientItem.isInStock());
        return ingredientItemRepository.save(ingredientItem);
    }

    @Override
    public IngredientItem createIngredientItem(Long restaurantId, String name, Long categoryId) throws Exception {
        Restaurant restaurant = restaurantService.findRestaurantById(restaurantId);
        IngredientCategory ingredientCategory = findIngredientCategoryById(categoryId);

        IngredientItem ingredientItem = new IngredientItem();
        ingredientItem.setRestaurant(restaurant);
        ingredientItem.setName(name);
        ingredientItem.setIngredientCategory(ingredientCategory);

        IngredientItem savedIngredientItem = ingredientItemRepository.save(ingredientItem);
        ingredientCategory.getIngredients().add(savedIngredientItem);

        return savedIngredientItem;
    }
}
