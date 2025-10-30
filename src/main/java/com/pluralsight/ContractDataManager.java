package com.pluralsight;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ContractDataManager {
        private static final String fileName = "contracts.txt";
        public void saveContract(Contract contract) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {

                String line = formatContractLine(contract);
                writer.write(line);
                writer.newLine();

            } catch (IOException e) {
                System.out.println("Error saving contract: " + e.getMessage());
            }
        }
        // Helper: builds the correct pipe-delimited line
        private String formatContractLine(Contract contract) {
            Vehicle vehicle = contract.getVehicleSold();
            // Common fields for all contracts
            String common = String.format("%s|%s|%s|%s|%d|%s|%s|%s|%s|%d|%.2f",
                    contract.getDate(),
                    contract.getCustomerName(),
                    contract.getCustomerEmail(),
                    vehicle.getVin(),
                    vehicle.getYear(),
                    vehicle.getMake(),
                    vehicle.getModel(),
                    vehicle.getVehicleType(),
                    vehicle.getColor(),
                    vehicle.getOdometer(),
                    vehicle.getPrice());

            // SALE format
            if (contract instanceof SalesContract) {
                SalesContract sc = (SalesContract) contract;
                return String.format("SALE|%s|%.2f|%.2f|%.2f|%.2f|%s|%.2f",
                        common,
                        sc.getSalesTax(),
                        sc.getRecordingFee(),
                        sc.getProcessingFee(),
                        sc.getTotalPrice(),
                        sc.getFinanceOptionString(),
                        sc.getMonthlyPayment());
            }
            // LEASE format
            else if (contract instanceof LeaseContract) {
                LeaseContract leaseContract = (LeaseContract) contract;
                return String.format("LEASE|%s|%.2f|%.2f|%.2f|%.2f",
                        common,
                        leaseContract.getExpectedEndingValue(),
                        leaseContract.getLeaseFee(),
                        leaseContract.getTotalPrice(),
                        leaseContract.getMonthlyPayment());
            }
            return "";
        }
    }

