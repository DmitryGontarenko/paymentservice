package com.acc.model;

import lombok.Data;

@Data
public class PaymentResponse {
    private String id; // идентификатор платежа
    private double commission; // сумма комиссии
}
