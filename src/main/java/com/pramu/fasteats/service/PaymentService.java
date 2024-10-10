package com.pramu.fasteats.service;

import com.pramu.fasteats.model.Order;
import com.pramu.fasteats.response.PaymentResponse;
import com.stripe.exception.StripeException;
import org.springframework.stereotype.Service;

@Service
public interface PaymentService {
    public PaymentResponse createPaymentLink(Order order) throws StripeException;
}
