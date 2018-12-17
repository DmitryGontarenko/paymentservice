package com.acc.client.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Payment {
    private String officeName;
    private LocalDateTime dateTime;
    private double amount;

    public Payment(String officeName) {
        this.officeName = officeName;
        this.dateTime = PaymentGenerate.generateRandomDate();
        this.amount = PaymentGenerate.generateRandomAmount();
    }
}
