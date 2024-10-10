package com.pramu.fasteats.request;

import lombok.Data;

@Data
public class AddonCategoryRequest {
    private String name;
    private Long restaurantId;
}
