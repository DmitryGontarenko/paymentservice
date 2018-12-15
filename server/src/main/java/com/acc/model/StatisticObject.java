package com.acc.model;

public class StatisticObject {

    private int numberOfPayments = 0; // номер платежа
    private double totalAmount; // сумма платежа
    private double totalCommission; // сумма комиссии

    public StatisticObject(double totalAmount, double totalCommission) {
        this.totalAmount = totalAmount;
        this.totalCommission = totalCommission;
    }

    public void addStatistic(double amount, double commission) {
        numberOfPayments++;
        totalAmount = amount;
        totalCommission = commission;
    }

    public int getNumberOfPayments() {
        return numberOfPayments;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public double getTotalCommission() {
        return totalCommission;
    }
}
