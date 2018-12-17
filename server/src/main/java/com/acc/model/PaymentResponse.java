package com.acc.model;

import lombok.Data;

@Data
public class PaymentResponse {
    private String id;
    private double commission;

    public void setCommission(double amount) {
        this.commission = amount * 0.0015;
    }
}
