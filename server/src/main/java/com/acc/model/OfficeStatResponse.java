package com.acc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OfficeStatResponse { // ответ по каждой точке продаж
    private String office; // точка продажи
    private int numberOfPayments; // количество платежей
    private double totalAmount; // сумма платежей
    private double totalCommission; // сумма комссии

}
