package com.pramu.fasteats.request;

import lombok.Data;

@Data
public class CartItemUpdateRequest {

    private Long cartItemId;
    private int quantity;

}
