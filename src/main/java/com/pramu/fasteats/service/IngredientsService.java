package com.pramu.fasteats.service;

import com.pramu.fasteats.model.IngredientCategory;
import com.pramu.fasteats.model.IngredientItem;

import java.util.List;

public interface IngredientsService {

    public IngredientCategory findIngredientCategoryById(Long id) throws Exception;

    public List<IngredientCategory> findIngredientCategoryByRestaurantId(Long id) throws Exception;

    public IngredientCategory createIngredientCategory(String name, Long restaurantId) throws Exception;

    public List<IngredientItem> findRestaurantIngredientItems(Long restaurantId) throws Exception;

    public IngredientItem ingredientItemStockUpdate(Long id) throws Exception;

    public IngredientItem createIngredientItem(Long restaurantId, String name, Long categoryId) throws Exception;

}
