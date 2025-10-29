package com.pluralsight;

import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface { // dealership object will store all cars and dealership info

    private Dealership dealership;
    private Scanner scanner;


    public UserInterface() {
        this(null);
    }

    public UserInterface(Dealership dealership) { // Constructor that accepts a dealership object
        this.dealership = dealership;
        this.scanner = new Scanner(System.in);
    }

    public void display() {
        if (dealership == null) {
            init();
        }
        boolean running = true;

        while (running) {
            displayMenu();
            String choice = ConsoleHelper.promptForString("Enter your choice").trim();
            switch (choice) {
                case "1":
                    processGetByPriceRequest();
                    break;
                case "2":
                    processGetByMakeModelRequest();
                    break;
                case "3":
                    processGetByYearRequest();
                    break;
                case "4":
                    processGetByColorRequest();
                    break;
                case "5":
                    processGetByMileageRequest();
                    break;
                case "6":
                    processGetByVehicleTypeRequest();
                    break;
                case "7":
                    processAllVehiclesRequest();
                    break;
                case "8":
                    processAddVehicleRequest();
                    break;
                case "9":
                    processRemoveVehicleRequest();
                    break;
                case "99":
                    running = false;
                    System.out.println("Exiting the program. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;

            }
        }
    }
    private void processGetByPriceRequest() {  // Find vehicles by price range
        double min = ConsoleHelper.promptForDouble("Enter minimum price");
        double max = ConsoleHelper.promptForDouble("Enter maximum price");
        displayVehicles(dealership.getVehiclesByPrice(min, max));
    }

    private void init() {  // Load dealership data from the file
        DealershipFileManager fileManager = new DealershipFileManager("inventory.csv");
        this.dealership = fileManager.getDealership();

        if (this.dealership == null) {
            System.out.println("Failed to load dealership data.");
            System.exit(1);
        } else {
            System.out.println("Dealership loaded successfully: " + dealership.getName());
        }
    }

    private void displayMenu() {
        System.out.println("\n===== Dealership Menu =====");
        System.out.println("1 : Find vehicles within a price range");
        System.out.println("2 : Find vehicles by make/model");
        System.out.println("3 : Find vehicles by year range");
        System.out.println("4 : Find vehicles by color");
        System.out.println("5 : Find vehicles by mileage range");
        System.out.println("6 : Find vehicles by type(car, truck, SUV, van)");
        System.out.println("7 : List ALL vehicles");
        System.out.println("8 : Add a vehicle");
        System.out.println("9 : Remove a vehicle");
        System.out.println("99 : Quit");
    }

    private void displayVehicles(ArrayList<Vehicle> vehicles) {
        if (vehicles.isEmpty()) {
            System.out.println("No vehicles found.");
            return;
        }

        System.out.printf("%-10s %-6s %-12s %-12s %-10s %-10s %-12s %-10s%n",
                "VIN", "Year", "Make", "Model", "Type", "Color", "Mileage", "Price");
        System.out.println("-----------------------------------------------------------------------------------");

        for (Vehicle v : vehicles) {
            System.out.printf("%-10s %-6d %-12s %-12s %-10s %-10s %-12d $%-10.2f%n",
                    v.getVin(), v.getYear(), v.getMake(), v.getModel(),
                    v.getVehicleType(), v.getColor(), v.getOdometer(), v.getPrice());
        }
    }

    private void processAllVehiclesRequest() {
        displayVehicles(dealership.getAllVehicles());
    }

    private void processGetByMakeModelRequest() {  // Finds vehicles by make and model
        String make = ConsoleHelper.promptForString("Enter make");
        String model = ConsoleHelper.promptForString("Enter model");
        displayVehicles(dealership.getVehiclesByMakeModel(make, model));
    }

    private void processGetByYearRequest() {  // Finds vehicles within a year range
        int min = ConsoleHelper.promptForInt("Enter minimum year");
        int max = ConsoleHelper.promptForInt("Enter maximum year");
        displayVehicles(dealership.getVehiclesByYear(min, max));
    }

    private void processGetByColorRequest() {
        String color = ConsoleHelper.promptForString("Enter color");
        displayVehicles(dealership.getVehiclesByColor(color));
    }

    private void processGetByMileageRequest() {
        int min = ConsoleHelper.promptForInt("Enter minimum mileage");
        int max = ConsoleHelper.promptForInt("Enter maximum mileage");
        displayVehicles(dealership.getVehiclesByMileage(min, max));
    }

    private void processGetByVehicleTypeRequest() { // Finds vehicles by type car,truck,Suv, van
        String type = ConsoleHelper.promptForString("Enter vehicle type (car/truck/SUV/van)");
        displayVehicles(dealership.getVehiclesByType(type));
    }

    private void processAddVehicleRequest() {  // Add new vehicle to the dealership
        System.out.println("\n===== Add New Vehicle =====");

        String vin = ConsoleHelper.promptForString("Enter VIN");
        int year = ConsoleHelper.promptForInt("Enter year");
        String make = ConsoleHelper.promptForString("Enter make");
        String model = ConsoleHelper.promptForString("Enter model");
        String type = ConsoleHelper.promptForString("Enter vehicle type");
        String color = ConsoleHelper.promptForString("Enter color");
        int odometer = ConsoleHelper.promptForInt("Enter odometer reading");
        double price = ConsoleHelper.promptForDouble("Enter price");

        Vehicle vehicle = new Vehicle(vin, year, make, model, type, color, odometer, price);
        dealership.addVehicle(vehicle);

        DealershipFileManager fileManager = new DealershipFileManager("inventory.csv");
        fileManager.saveDealership(dealership);

        System.out.println("Vehicle added successfully!");
    }

    private void processRemoveVehicleRequest() {
        String vin = ConsoleHelper.promptForString("Enter VIN of vehicle to remove");

        Vehicle toRemove = null;
        for (Vehicle v : dealership.getAllVehicles()) {  // Search for a matching vin in the dealership
            if (v.getVin().equalsIgnoreCase(vin)) {
                toRemove = v;
                break;
            }
        }
        if (toRemove != null) {  // If you find it, remove it and save the file
            dealership.removeVehicle(toRemove);

            new DealershipFileManager("inventory.csv").saveDealership(dealership);
            System.out.println("Vehicle removed successfully!");
        } else {
            System.out.println("Vehicle with VIN " + vin + " vin not found.");  // Remove a vehicle using its VIN number
        }
    }
}
