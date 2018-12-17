package com.acc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataStatResponse {
    private LocalDate date;
    private int numberOfPayments;
    private double totalAmount;
    private double totalCommission;
}
