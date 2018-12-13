package com.acc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataStatResponse {
    private LocalDate date; // дата
    private int numberOfPayments; // количество платажей
    private double totalAmount; // сумма платежей
    private double totalCommission; // сумма комиссий
}
