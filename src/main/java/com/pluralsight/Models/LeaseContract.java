package com.pluralsight.Models;

public class LeaseContract extends Contract {

    private double leaseFee;
    private double expectedEndingValue;

    public LeaseContract(String date, String customerName, String customerEmail,
                         Vehicle vehicleSold) {
        super(date, customerName, customerEmail, vehicleSold);
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

//    @Override
//    public String toString() {
//        return "LeaseContract{" +
//                "leaseFee=" + leaseFee +
//                ", expectedEndingValue=" + expectedEndingValue +
//                '}';


    @Override
    public String toString() {
        return String.format(
                "\n=== Lease Details ===\n" +
                        "Vehicle Price: $%.2f%n" +
                        "Expected Ending Value: $%.2f%n" +
                        "Lease Fee: $%.2f%n" +
                        "Total Price: $%.2f%n" +
                        "Monthly Payment: $%.2f%n",
                getVehicleSold().getPrice(),
                getExpectedEndingValue(),
                getGetLeaseFee(),
                getTotalPrice(),
                getMonthlyPayment()
        );
    }
}
