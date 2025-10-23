package com.pluralsight;

public class Main {
    public static void main(String[] args) {
        Dealership dealer = new Dealership("Kia Dealership", "11410 ", "509-515-1603");

        Vehicle car = new Vehicle("AVG423", "jeep", "Big", 2025, 2500);
        dealer.addVehicle(car);

        System.out.println("Vehicles in inventory: " + dealer.getAllVehicles().size());

        }
    }
