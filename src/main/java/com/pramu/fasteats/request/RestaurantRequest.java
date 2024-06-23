package com.pramu.fasteats.request;

import com.pramu.fasteats.model.Address;
import com.pramu.fasteats.model.ContactInformation;
import lombok.Data;

import java.util.List;

@Data
public class RestaurantRequest {

    private Long id;
    private String name;
    private Address address;
    private String description;
    private ContactInformation contactInformation;
    private List<String> images;
    private String cuisineType;
    private String openingHours;

}
