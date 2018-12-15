package com.acc.client.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Payment {
    private String officeName; // точка продажи
    private LocalDateTime dateTime; // дата
    private double amount; // сумма операции

    public Payment(String officeName) {
        this.officeName = officeName; // присваиваем имя точки продаж
        this.dateTime = PaymentGenerate.generateRandomDate(); // присваиваем сгенерированную случайно дату
        this.amount = PaymentGenerate.generateRandomAmount(); // присваиваем сгенерированную случайно сумму
    }
}
