package com.acc.model;

public class StatisticsObject {

    private int numberOfPayments = 0;
    private double totalAmount;
    private double totalCommission;

    public StatisticsObject(double totalAmount, double totalCommission) {
        this.totalAmount = totalAmount;
        this.totalCommission = totalCommission;
    }

    public void addStatistics(double amount, double commission) {
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
