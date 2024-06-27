package com.pramu.fasteats.request;

import lombok.Data;

import java.util.List;

@Data
public class CartItemRequest {

    private Long foodId;
    private int quantity;
    private List<String> ingredients;

}
