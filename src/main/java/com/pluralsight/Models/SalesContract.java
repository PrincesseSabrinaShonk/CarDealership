package com.pluralsight.Models;

public class SalesContract extends Contract {
    private double salesTaxAmount;
    private double recordingFee = 100.00;
    private double processingFee;
    private boolean financeOption;

    public SalesContract(String date, String customerName, String customerEmail,
                         Vehicle vehicleSold, boolean financeOption) {
        super(date, customerName, customerEmail, vehicleSold);
        this.financeOption = financeOption;

        this.salesTaxAmount = vehicleSold.getPrice() * 0.05;

        if (vehicleSold.getPrice() < 10000){
            this.processingFee = 295;
        } else{
            this.processingFee = 495;
        }
    }

    public double getSalesTaxAmount() {
        return salesTaxAmount;
    }

    public double getRecordingFee() {
        return recordingFee;
    }

    public double getProcessingFee() {
        return processingFee;
    }

    public boolean isWantToFinance() {
        return financeOption;
    }

    @Override
    public double getTotalPrice() {
        return getVehicleSold().getPrice() + salesTaxAmount + recordingFee + processingFee;
    }
    @Override
    public double getMonthlyPayment() {
        if (!financeOption) {
            return 0.0;
        }
        double price = getTotalPrice();
        double monthlyRate;
        int months;

        if(getVehicleSold().getPrice() >= 10000) {
            monthlyRate = 0.0425 / 12;
            months = 48;
        } else {
            monthlyRate = 0.0525 / 12;
            months = 24;
        }

        return price * (monthlyRate * Math.pow(1 + monthlyRate, months))/
                (Math.pow(1 + monthlyRate, months) - 1);
    }
    @Override
    public String toString() {
        String result= String.format(
                "\n=== Sale ===%n" +
                        "Vehicle Price: $%.2f%n" +
                        "Sales Tax: $%.2f%n" +
                        "Recording fee: $%.2f%n" +
                        "Processing fee: $%.2f%n" +
                        "Total Price: $%.2f%n",
                getVehicleSold().getPrice(),
                getSalesTaxAmount(),
                getRecordingFee(),
                getProcessingFee(),
                getTotalPrice()
        );
        if (financeOption) {
            result += String.format("Monthly Payment: $%.2f%n", getMonthlyPayment());
        } else {
            result += "Paying in full – no monthly payment\n";
        }
        return result;
    }

}