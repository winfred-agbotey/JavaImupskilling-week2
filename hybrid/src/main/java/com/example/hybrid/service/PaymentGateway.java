package com.example.hybrid.service;

import com.example.hybrid.model.Order;
import org.springframework.stereotype.Service;

@Service
public class PaymentGateway {
    public boolean processPayment(Order order) {
        return true;
    }
}
