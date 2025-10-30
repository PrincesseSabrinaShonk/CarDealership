package com.pluralsight;

public class SalesContract extends Contract {
    private double saleTaxAmount;
    private double recordingFee = 100.00;
    private double processingFee;
    private boolean financeOption;

    public SalesContract(String date, String customerName, String customerEmail,
                         Vehicle vehicleSold, double saleTaxAmount,
                         double recordingFee, double processingFee, boolean financeOption) {
        super(date, customerName, customerEmail, vehicleSold);
        this.saleTaxAmount = saleTaxAmount;
        this.recordingFee = recordingFee;
        this.processingFee = processingFee;
        this.financeOption = financeOption;
    }

    public boolean isFinanceOption() {
        return financeOption;
    }

    public void setFinanceOption(boolean financeOption) {
        this.financeOption = financeOption;
    }

    @Override
    public double getTotalPrice() {
        double price = getVehicleSold().getPrice();
        double salesTax = price * 0.05;
        double recordingFee = 100.00;
        double processingFee = (price < 10000) ? 295.00 : 495.00;

        return price + salesTax + recordingFee + processingFee;
    }

    @Override
    public double getMonthlyPayment() {
        if (!financeOption) return 0.0;
        double totalPrice = getTotalPrice();
        double interestRate;
        int months;
        if (totalPrice >= 10000) {
            interestRate = 4.25 / 100 / 12;
            months = 48;
        } else {
            interestRate = 5.25 / 100 / 12;
            months = 24;
        }
        double r = interestRate;
        double n = months;
        return totalPrice * r * Math.pow(1 + r, n) / (Math.pow(1 + r, n) - 1);
    }
    // Helper methods for file output
    public double getSalesTax() {
        return getVehicleSold().getPrice() * 0.05;
    }
    public double getRecordingFee() {
        return 100.00;
    }
    public double getProcessingFee() {
        return (getVehicleSold().getPrice() < 10000) ? 295.00 : 495.00;
    }
    public String getFinanceOptionString() {
        return financeOption ? "YES" : "NO";
    }
}

