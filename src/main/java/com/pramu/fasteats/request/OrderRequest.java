package com.pramu.fasteats.request;

import com.pramu.fasteats.model.Address;
import lombok.Data;

@Data
public class OrderRequest {

    private Long restaurantId;
    private Address deliveryAddress;
}
