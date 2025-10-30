package com.pluralsight;

public class LeaseContract extends Contract {
    public LeaseContract(String date, String customerName, String customerEmail, Vehicle vehicleSold) {
        super(date, customerName, customerEmail, vehicleSold);
    }

    @Override
    public double getTotalPrice() {
        double originalPrice = getVehicleSold().getPrice();
        double expectedEndingValue = originalPrice * 0.5;
        double leaseFee = originalPrice * 0.07;
        return originalPrice - expectedEndingValue + leaseFee;
    }
    @Override
    public double getMonthlyPayment() {
    double originalPrice = getVehicleSold().getPrice();
    double expectedEndingValue = originalPrice * 0.5;
    double totalDepreciation = originalPrice - expectedEndingValue;
    double leaseFee = originalPrice * 0.07;

    double totalCost = totalDepreciation + leaseFee;
    double interestRate = 4.0 / 100 / 12;  // 4% annual → monthly
    int months = 36;

    double r = interestRate;
    double n = months;
    return totalCost * r / (1 - Math.pow(1 + r, -n));
    }
    // Helper methods for file output
    public double getExpectedEndingValue() {
        return getVehicleSold().getPrice() * 0.5;
    }

    public double getLeaseFee() {
        return getVehicleSold().getPrice() * 0.07;
    }
}
