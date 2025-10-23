package com.pluralsight;
import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {

    private Dealership dealership; // This will hold our loaded dealership
    private Scanner scanner;       // Scanner will read user input

    public UserInterface() {
        scanner = new Scanner(System.in);
    }

    public void display() {
        // Step 1: Load dealership data
        init();

        boolean running = true;
        while (running) {
            displayMenu();
            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    processAllVehiclesRequest();
                    break;
                case "0":
                    running = false; // exit the program
                    System.out.println("Exiting the program. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }
    /**
     * Loads the dealership from the CSV file using DealershipFileManager
     */
    private void init() {
        DealershipFileManager fileManager = new DealershipFileManager("inventory.csv");
        this.dealership = fileManager.getDealership();
        System.out.println("Dealership loaded successfully!");
    }

    /**
     * Displays a list of vehicles.
     */
    private void displayVehicles(ArrayList<Vehicle> vehicles) {
        if (vehicles.isEmpty()) {
            System.out.println("No vehicles to display.");
            return;
        }
        System.out.println("\n===== Vehicle Inventory =====");
        System.out.printf("%-10s %-6s %-12s %-12s %-10s%n",
                "VIN", "Year", "Make", "Model", "Price");
        System.out.println("-------------------------------------------");

        for (Vehicle v : vehicles) {
            System.out.println(v.getVin() + "    "  // 4 spaces
                    + v.getYear() + "    "
                    + v.getMake() + "    "
                    + v.getModel() + "    $"
                    + v.getPrice());
        }
    }

    private void displayMenu() {
        System.out.println("\n===== Dealership Menu =====");
        System.out.println("1. List all vehicles");
        System.out.println("0. Exit");
    }

    /**
     * Processes the "list all vehicles" request.
     */
    private void processAllVehiclesRequest() {
        ArrayList<Vehicle> allVehicles = dealership.getAllVehicles();
        displayVehicles(allVehicles);
    }


    private void processGetByMakeRequest() {

    }
    private void processGetByPriceRequest() {

    }
    private void processAddVehicleRequest() {

    }
    private void processRemoveVehicleRequest() {

    }
}

