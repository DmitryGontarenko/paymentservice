package com.acc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class OfficeStatResponse extends DataOfficeStatResponse {
    private String office;

    public OfficeStatResponse() {
    }

    public OfficeStatResponse(int numberOfPayments, double totalAmount, double totalCommission, String office) {
        super(numberOfPayments, totalAmount, totalCommission);
        this.office = office;
    }
}
