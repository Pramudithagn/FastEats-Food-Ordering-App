package com.pramu.fasteats.model;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class ContactInformation {
    private String email;
    private String phone;
    private String instagram;
    private String facebook;
}
