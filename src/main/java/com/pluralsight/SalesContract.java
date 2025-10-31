package com.pluralsight;

public class SalesContract extends Contract{
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
}