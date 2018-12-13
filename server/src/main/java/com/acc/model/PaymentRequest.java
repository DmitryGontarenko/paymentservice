package com.acc.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PaymentRequest {
    private LocalDateTime dateTime; // дата и время приема платежа
    private double amount; // сумма платежа
    private String officeName; // наименование точки продаж
}
