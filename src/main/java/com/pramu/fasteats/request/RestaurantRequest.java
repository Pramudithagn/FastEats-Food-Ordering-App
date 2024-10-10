package com.pramu.fasteats.request;

import com.pramu.fasteats.model.Address;
import com.pramu.fasteats.model.ContactInformation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantRequest {
    private String name;
    private Address address;
    private String description;
    private ContactInformation contactInformation;
    private List<String> images;
    private String cuisineType;
    private String openingHours;
}
