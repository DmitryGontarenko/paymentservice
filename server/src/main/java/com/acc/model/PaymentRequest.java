package com.acc.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PaymentRequest {
    private LocalDateTime dateTime;
    private double amount;
    private String officeName;
}
