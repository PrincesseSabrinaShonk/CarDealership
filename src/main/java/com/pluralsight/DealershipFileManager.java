package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DealershipFileManager { // This class handles reading and saving the dealership data from a file.
    private String fileName;

    //Constructor that sets the file to read and write
    public DealershipFileManager(String filename) {
        this.fileName = filename;
    }

    public Dealership getDealership() {
        Dealership dealership = null;

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;

            if ((line = br.readLine()) != null) {
                String[] dealerParts = line.split("\\|");
                String name = dealerParts[0];
                String address = dealerParts[1];
                String phone = dealerParts[2];
                dealership = new Dealership(name, address, phone);
            }
           //Read remaining lines from vehicle inventory
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 8) {  // Changed from 5 to 8
                    String vin = parts[0];
                    int year = Integer.parseInt(parts[1]);
                    String make = parts[2];
                    String model = parts[3];
                    String vehicleType = parts[4];
                    String color = parts[5];
                    int odometer = Integer.parseInt(parts[6]);
                    double price = Double.parseDouble(parts[7]);

                    Vehicle vehicle = new Vehicle(vin, year, make, model, vehicleType, color, odometer, price);
                    dealership.addVehicle(vehicle);

                }
            }
        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        }

        return dealership;
    }
    public void saveDealership(Dealership dealership){

    }

    }
