package com.pramu.fasteats.request;

import lombok.Data;

@Data
public class IngredientItemRequest {

    private String name;
    private Long ingredientCategoryId;
    private Long restaurantId;

}
