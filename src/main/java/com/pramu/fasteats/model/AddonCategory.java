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
public class AddonCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @ToString.Exclude
    @JsonIgnore
    @OneToMany(mappedBy = "addonCategory", cascade = CascadeType.ALL)
    private List<AddonItem> addons = new ArrayList<>();
    @ToString.Exclude
    @JsonIgnore
    @ManyToOne
    private Restaurant restaurant;
}

