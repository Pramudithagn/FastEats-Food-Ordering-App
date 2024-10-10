package com.pramu.fasteats.response;

import com.pramu.fasteats.model.USER_ROLE;
import lombok.Data;

@Data
public class AuthResponse {
    private String token;
    private String message;
    private USER_ROLE role;
}
