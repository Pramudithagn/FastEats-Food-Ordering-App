package com.pramu.fasteats.response;

import lombok.Data;

@Data
public class PaymentResponse {
    private String payment_url;
    private Long orderId;
}
