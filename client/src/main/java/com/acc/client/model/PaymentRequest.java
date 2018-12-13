package com.acc.client.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PaymentRequest {
    private String officeName;
    private LocalDateTime dateTime;
    private double sum;

    public PaymentRequest(String officeName) {
        this.officeName = officeName;
        this.dateTime = PaymentGenerated.generatedRandomDate();
        this.sum = PaymentGenerated.generatedRandomSum();
    }
}
