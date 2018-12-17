package com.acc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DataOfficeStatResponse {
    protected int numberOfPayments;
    protected double totalAmount;
    protected double totalCommission;
}
