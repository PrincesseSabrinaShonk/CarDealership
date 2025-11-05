package com.pluralsight.Data;

import com.pluralsight.Models.Contract;
import com.pluralsight.Models.LeaseContract;
import com.pluralsight.Models.SalesContract;
import com.pluralsight.Models.Vehicle;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ContractFileManager {
    // File name for storing all contracts
    private static final String fileName = "contracts.csv";

    public void saveContract(Contract contract) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("contracts.csv", true))) {
            // Format the contract as a pipe-delimited line
            String line = formatContractLine(contract);
            writer.write(line);
            writer.newLine();

            System.out.println("Contract saved successfully!");
        } catch (IOException e) {
            System.out.println("Error saving contract: " + e.getMessage());
        }
    }

    private String formatContractLine(Contract contract) {
        Vehicle v = contract.getVehicleSold();

        // Common fields for all contracts
        String common = String.format("%s|%s|%s|%s|%d|%s|%s|%s|%s|%d|%.2f",
                contract.getDate(),
                contract.getCustomerName(),
                contract.getCustomerEmail(),
                v.getVin(),
                v.getYear(),
                v.getMake(),
                v.getModel(),
                v.getVehicleType(),
                v.getColor(),
                v.getOdometer(),
                v.getPrice());

        // Check if this is a SalesContract
        if (contract instanceof SalesContract) {
            SalesContract sc = (SalesContract) contract;
            String financeOption = sc.isWantToFinance() ? "YES" : "NO";
            return String.format("SALE|%s|%.2f|%.2f|%.2f|%s|%.2f|%.2f",
                    common,
                    sc.getSalesTaxAmount(),
                    sc.getRecordingFee(),
                    sc.getProcessingFee(),
                    financeOption,
                    sc.getTotalPrice(),
                    sc.getMonthlyPayment());
        }
        // Check if this is a LeaseContract
        else if (contract instanceof LeaseContract) {
            LeaseContract lc = (LeaseContract) contract;
            return String.format("LEASE|%s|%.2f|%.2f|%.2f|%.2f",
                    common,
                    lc.getExpectedEndingValue(),
                    lc.getGetLeaseFee(),
                    lc.getTotalPrice(),
                    lc.getMonthlyPayment());
        }
        return "";
    }
}