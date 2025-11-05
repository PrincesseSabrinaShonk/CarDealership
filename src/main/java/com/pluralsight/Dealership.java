package com.pluralsight;

import java.util.ArrayList;  //hold dealership inf of all vehicle the dealership currently has for sale.
import java.util.List;

public class Dealership {
    private String name;
    private String address;
    private String phone;
    private ArrayList<Vehicle> inventory; // List of all vehicles in the dealership

    public Dealership(String name, String address, String phone) { // create a new dealership with name address phone
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.inventory = new ArrayList<>();
    }

    public String getPhone() {
        return phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    // create the method that Add a vehicle
    public void addVehicle(Vehicle vehicle) {
        inventory.add(vehicle);
    }

    public ArrayList<Vehicle> getAllVehicles() {
        return inventory;
    }


    public ArrayList<Vehicle> getVehiclesByMakeModel(String make, String model) { // Find vehicles by make and model
        ArrayList<Vehicle> results = new ArrayList<>();
        for (Vehicle vehicle : inventory) {
            if (vehicle.getMake().equalsIgnoreCase(make) &&
                    vehicle.getModel().equalsIgnoreCase(model)) {
                results.add(vehicle);
            }
        }
        return results;
    }

    public ArrayList<Vehicle> getVehiclesByPrice(double min, double max) {    // Find vehicles by price range
        ArrayList<Vehicle> results = new ArrayList<>();
        for (Vehicle vehicle : inventory) {
            if (vehicle.getPrice() >= min && vehicle.getPrice() <= max){
                results.add(vehicle);
        }
    }
    return results;
    }
    public ArrayList<Vehicle> getVehiclesByYear(int min, int max) {  // Find vehicles by year range
        ArrayList<Vehicle> results = new ArrayList<>();
        for (Vehicle vehicle : inventory) {
            if (vehicle.getYear() >= min && vehicle.getYear() <= max) {
                results.add(vehicle);
            }
        }
        return results;
    }

    public void removeVehicle(Vehicle vehicle) {
        inventory.remove(vehicle);  // remove vehicle from list
    }

    public ArrayList<Vehicle> getVehiclesByColor(String color){ // Find vehicle  by color
        ArrayList<Vehicle> results = new ArrayList<>();
        for (Vehicle vehicle : inventory) {
            if (vehicle.getColor().equalsIgnoreCase(color)) {
                results.add(vehicle);
            }
        }
        return results;
    }

    public ArrayList<Vehicle> getVehiclesByMileage(int min, int max) { // Find vehicles by mileage range
        ArrayList<Vehicle> results = new ArrayList<>();
        for (Vehicle vehicle : inventory) {
            if (vehicle.getOdometer() >= min && vehicle.getOdometer() <= max) {
                results.add(vehicle);
            }
        }
        return results;

    }
    public ArrayList<Vehicle> getVehiclesByType( String vehicleType) { // Find vehicles by type
        ArrayList<Vehicle> results = new ArrayList<>();
        for (Vehicle vehicle : inventory) {
            if (vehicle.getVehicleType().equalsIgnoreCase(vehicleType)) {
                results.add(vehicle);
            }
        }
        return results;


//    public List<Vehicle> getVehiclesByType(String vehicleType) { // Find vehicles by type
//      return inventory.stream().filter(v -> v.getVehicleType().equalsIgnoreCase(vehicleType)).toList();
//    }
//
//    public List<Vehicle> getVehiclesByMileage(int min, int max){
//        return inventory.stream().filter(v -> v.getOdometer() >= min && v.getOdometer() <= max).toList();
//    }
    }

    @Override
    public String toString() {
        return "Dealership{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
    public Vehicle getVehicleByVin(String vin) {
        for (Vehicle vehicle : inventory) {
            if (vehicle.getVin().equals(vin)) {
                return vehicle;
            }
        }
        return null;

       }
    }
