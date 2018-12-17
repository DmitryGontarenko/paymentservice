package com.acc.model;

import lombok.Data;

import java.util.UUID;

@Data
public class PaymentResponse {
    private String id;
    private double commission;

    public PaymentResponse(double amount) {
        this.id = UUID.randomUUID().toString();
        this.commission = amount * 0.0015;
    }
}
