package com.pramu.fasteats.request;

import lombok.Data;

@Data
public class AddonItemRequest {
    private String name;
    private Long addonCategoryId;
    private Long restaurantId;
}
