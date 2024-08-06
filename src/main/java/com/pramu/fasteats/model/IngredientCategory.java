package com.pramu.fasteats.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class IngredientCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @ToString.Exclude
    @JsonIgnore
    @OneToMany(mappedBy = "ingredientCategory", cascade = CascadeType.ALL)
    private List<IngredientItem> ingredients = new ArrayList<>();

    @ToString.Exclude
    @JsonIgnore
    @ManyToOne
    private Restaurant restaurant;
}

