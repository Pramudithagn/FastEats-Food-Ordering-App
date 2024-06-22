package com.pramu.fasteats.model;

import jakarta.persistence.Embeddable;

@Embeddable
public class ContactInformation {

    private String email;
    private String phone;
    private String instagram;
    private String facebook;
}
