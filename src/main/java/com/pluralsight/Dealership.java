package com.pluralsight;
//holds dealership info and a list of vehicles.
import java.util.ArrayList;

public class Dealership {
    private String name;
    private String address;
    private String phone;
    private ArrayList<Vehicle> inventory; // List of all vehicles in the dealership

    public Dealership(String name, String address, String phone) { // create a nnew dealership with name address phone
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.inventory = new ArrayList<>();// Always initialize your ArrayList
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

    // create the method
    public void addVehicle(Vehicle vehicle) {
        inventory.add(vehicle);
    }

    public ArrayList<Vehicle> getAllVehicles() {
        return inventory;
    }


    public ArrayList<Vehicle> getVehiclesByMake(String make) {
        return null;
    }

    public ArrayList<Vehicle> getVehiclesByPrice(double min, double max) {
        return null;
    }

    public ArrayList<Vehicle> getVehiclesByYear(int min, int max) {
        return null;
    }

    public void removeVehicle(String vin) {
        // leave empty for now
    }
}