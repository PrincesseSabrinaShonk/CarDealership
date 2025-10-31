package com.pluralsight;

public class LeaseContract extends Contract {

    private double leaseFee;
    private double expectedEndingValue;

    public LeaseContract(String date, String customerName, String customerEmail,
                         Vehicle vehicleSold, double getLeaseFee, double expectedEndingValue ) {
        super(date, customerName, customerEmail, vehicleSold);
        this.expectedEndingValue = expectedEndingValue;
        this.leaseFee = getLeaseFee;
        double price = vehicleSold.getPrice();
        this.expectedEndingValue = price * 0.50;
        this.leaseFee = price * 0.07;
    }

    public double getExpectedEndingValue() {
        return expectedEndingValue;
    }

    public double getGetLeaseFee() {
        return leaseFee;
    }

    @Override
    public double getTotalPrice() {
        double price = getVehicleSold().getPrice();
        return price + leaseFee;
    }
    @Override
    public double getMonthlyPayment() {
        double price = getVehicleSold().getPrice() - expectedEndingValue + leaseFee;
        double monthlyRate = 0.04 / 12;
        int months = 36;
        return price * (monthlyRate * Math.pow(1 + monthlyRate, months)) /
                (Math.pow(1 + monthlyRate, months) - 1);
    }
}
