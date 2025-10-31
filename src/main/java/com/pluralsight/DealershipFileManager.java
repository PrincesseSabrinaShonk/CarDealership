package com.pluralsight;

import java.io.*;

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
                if (dealerParts.length >= 3) {
                    String name = dealerParts[0];
                    String address = dealerParts[1];
                    String phone = dealerParts[2];
                    dealership = new Dealership(name, address, phone);
                } else {
                    System.out.println("Invalid dealership name : " + line);
                    dealership = new Dealership("Ford", " 1234 main street", "000-000-5531");
                }
            } else {
                System.out.println("File is Empty");
                dealership = new Dealership("Ford", " 1234 main street", "000-000-1234");

            }
            //Read remaining lines from vehicle inventory
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 8) {
                    try {
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
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid Number");
                    }
                } else {
                    System.out.println("Skipping line with wrong number of fields: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println(" Error reading the file:" + e.getMessage());
            if (dealership == null) {
                dealership = new Dealership("Ford", " 1234 main street", "000-000-0000");
            }
        }
        return dealership;
    }

    public void saveDealership(Dealership dealership) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            // Write dealership info first
            bw.write(dealership.getName() + "|" +
                    dealership.getAddress() + "|" +
                    dealership.getPhone());
            bw.newLine();

            // Write all vehicles
            for (Vehicle vehicle : dealership.getAllVehicles()) {
                bw.write(vehicle.getVin() + "|" +
                        vehicle.getYear() + "|" +
                        vehicle.getMake() + "|" +
                        vehicle.getModel() + "|" +
                        vehicle.getVehicleType() + "|" +
                        vehicle.getColor() + "|" +
                        vehicle.getOdometer() + "|" +
                        vehicle.getPrice());
                bw.newLine();
            }

            System.out.println("Dealership saved successfully!");

        } catch (IOException e) {
            System.out.println("Error saving dealership: " + e.getMessage());
        }
    }
    }

