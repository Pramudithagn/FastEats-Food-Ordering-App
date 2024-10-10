package com.pramu.fasteats.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String image;
    private String startedAt;
    private String endsAt;
    private String name;
    @ManyToOne
    private Restaurant restaurant;
    private String location;
}
