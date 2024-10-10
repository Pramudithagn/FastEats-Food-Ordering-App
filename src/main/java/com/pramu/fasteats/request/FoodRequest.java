package com.pramu.fasteats.request;

import com.pramu.fasteats.model.AddonItem;
import com.pramu.fasteats.model.Category;
import lombok.Data;

import java.util.List;

@Data
public class FoodRequest {
    private String name;
    private String description;
    private Long price;
    private boolean vegetarian;
    private boolean seasonal;
    private List<AddonItem> addons;
    private List<String> images;
    private Category category;
    private Long restaurantId;
}
