package com.pramu.fasteats.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class IngredientItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private boolean inStock = true;

    @ToString.Exclude
    @JsonIgnore
    @ManyToOne
    private Restaurant restaurant;

    @ToString.Exclude
    @ManyToOne
    private IngredientCategory ingredientCategory;

}
