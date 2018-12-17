package com.acc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
public class DataStatResponse extends DataOfficeStatResponse {
    private LocalDate date;

    public DataStatResponse() {
    }

    public DataStatResponse(int numberOfPayments, double totalAmount, double totalCommission, LocalDate date) {
        super(numberOfPayments, totalAmount, totalCommission);
        this.date = date;
    }

}
