package com.pramu.fasteats.request;

import com.pramu.fasteats.model.Category;
import com.pramu.fasteats.model.IngredientItem;
import lombok.Data;

import java.util.List;

@Data
public class FoodRequest {

    private String name;
    private String description;
    private Long price;
    private boolean vegetarian;
    private boolean seasonal;
    private List<IngredientItem> ingredients;
    private List<String> images;
    private Category category;
    private Long restaurantId;

}
